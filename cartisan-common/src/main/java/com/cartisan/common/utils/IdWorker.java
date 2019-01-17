package com.cartisan.common.utils;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 * <p>Title: IdWorker</p>
 * <p>Description: 分布式自增长ID</p>
 *
 * <pre>
 *     Twitter的 Snowflake　JAVA实现方案
 * </pre>
 *
 * <p>
 * 0---0000000000 0000000000 0000000000 0000000000 0 --- 00000 ---00000 ---000000000000
 * 第 1 位为符号位，固定为 0，表示正数
 * 后 41 位代表从指定时间以来的时间戳
 * 后 5 位为 数据中心 标识位
 * 然后 5 位为机器ID（并不算标识符，实际是为线程标识）
 * 最后 12 位为该毫秒内的计数
 * 加起来正好 64 位，转换为一个 Long 型
 * </p>
 * <p>
 * 这样的好处是，整体上按照时间自增排序，并且整个分布式系统内不会产生 ID 碰撞（由 数据中心 和 机器ID 作区分）
 * snowflake 每秒能产生 26 万 ID 左右
 * </p>
 *
 * @author colin
 */


public class IdWorker {
    /**
     * 时间起始标记点，作为基准（一旦确定，不能变动）
     */
    private final static long twepoch = 1254648300000L;

    /**
     * 机器标识位数
     */
    private final static long workerIdBits = 5L;

    /**
     * 数据中心识位数
     */
    private final static long dataCenterIdBits = 5L;

    /**
     * 机器Id最大值
     */
    private final static long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /**
     * 数据中心Id最大值
     */
    private final static long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);

    /**
     * 毫秒内自增位
     */
    private final static long sequenceBits = 12L;

    /**
     * 机器ID偏左移 12 位
     */
    private final static long workerIdLeftShift = sequenceBits;

    /**
     * 数据中心ID左移 17 位
     */
    private final static long dataCenterIdLeftShift = sequenceBits + workerIdBits;

    /**
     * 时间毫秒左移 22 位
     */
    private final static long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;

    private final static long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * 上次生产ID的时间戳
     */
    private static long lastTimestamp = -1L;

    /**
     * 并发控制
     */
    private long sequence = 0L;

    private final long workerId;
    private final long dataCenterId;

    public IdWorker() {
        this.dataCenterId = getDataCenterId(maxDataCenterId);
        this.workerId = getWorkerId(dataCenterId, maxWorkerId);
    }

    public IdWorker(long workerId, long dataCenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("workerId 不能大于 %d 或者小于 0。", maxWorkerId));
        }
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("dataCenterId 不能大于 %d 或者小于 0。", maxDataCenterId));
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    /**
     * 获取下一个 ID
     * @return
     */
    public synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("时钟倒退，拒绝在 %d 毫秒内生成 Id。", lastTimestamp - timestamp));
        }

        if (lastTimestamp == timestamp) {
            // 当前毫秒内，则 +1
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                // 当前毫秒内满了，则等待下一秒
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        lastTimestamp = timestamp;

        // ID 偏移组合生成最终的 ID
        long nextId = ((timestamp - twepoch) << timestampLeftShift)
                | (dataCenterId << dataCenterIdLeftShift)
                | (workerId << workerIdLeftShift)
                | sequence;

        return nextId;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 获取 WorkerId
     */
    private static long getWorkerId(long dataCenterId, long maxWorkerId) {
        StringBuffer mpid = new StringBuffer();
        mpid.append(dataCenterId);
        String name = ManagementFactory.getRuntimeMXBean().getName();
        if (!name.isEmpty()) {
            /**
             * 获取 jvmPid
             */
            mpid.append(name.split("@")[0]);
        }

        /**
         * MAC + PID 的 hashcode 获取 16 个低位
         */
        return (mpid.toString().hashCode() & 0xffff) % (maxWorkerId + 1);
    }

    /**
     * 获取数据中心Id
     */
    private static long getDataCenterId(long maxDataCenterId) {
        long id = 0L;
        try {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            if (network == null) {
                id = 1L;
            } else {
                byte[] mac = network.getHardwareAddress();
                id = ((0x000000FF & (long) mac[mac.length - 1]) | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
                id = id % (maxDataCenterId + 1);
            }
        } catch (Exception e) {
            System.out.println(" getDataCenterId: " + e.getMessage());
        }

        return id;
    }

    public static void main(String[] args) {
        IdWorker idWorker = new IdWorker();

        for (int i = 0; i < 10000; i++) {
            System.out.println(idWorker.nextId());
        }
    }
}
