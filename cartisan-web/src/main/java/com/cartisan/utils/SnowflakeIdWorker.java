package com.cartisan.utils;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 * <p>Title: SnowflakeIdWorker</p>
 * <p>Description: 雪花算法分布式ID生成器</p>
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


public class SnowflakeIdWorker {
    /**
     * 开始时间截，作为基准（一旦确定，不能变动）
     */
    private final static long twepoch = 1254648300000L;

    /**
     * 机器id所占的位数
     */
    private final static long workerIdBits = 5L;

    /**
     * 数据标识id所占的位数
     */
    private final static long dataCenterIdBits = 5L;

    /**
     * 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     */
    private final static long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /**
     * 支持的最大数据标识id，结果是31
     */
    private final static long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);

    /**
     * 序列在id中占的位数
     */
    private final static long sequenceBits = 12L;

    /**
     * 机器ID向左移12位
     */
    private final static long workerIdLeftShift = sequenceBits;

    /**
     * 数据标识id向左移17位(12+5)
     */
    private final static long dataCenterIdLeftShift = sequenceBits + workerIdBits;

    /**
     * 时间截向左移22位(5+5+12)
     */
    private final static long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;

    /**
     * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
     */
    private final static long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * 工作机器ID(0~31)
     */
    private final long workerId;

    /**
     * 数据中心ID(0~31)
     */
    private final long dataCenterId;

    /**
     * 并发控制，毫秒内序列(0~4095)
     */
    private long sequence = 0L;

    /**
     * 上次生产ID的时间戳
     */
    private static long lastTimestamp = -1L;

    /**
     * 构造函数
     */
    public SnowflakeIdWorker() {
        this.dataCenterId = getDataCenterId(maxDataCenterId);
        this.workerId = getWorkerId(dataCenterId, maxWorkerId);
    }

    /**
     * 构造函数
     * @param workerId 工作ID (0~31)
     * @param dataCenterId 数据中心ID (0~31)
     */
    public SnowflakeIdWorker(long workerId, long dataCenterId) {
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
     * 获得下一个ID (该方法是线程安全的)
     * @return SnowflakeId
     */
    public synchronized long nextId() {
        long timestamp = timeGen();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("时钟倒退，拒绝在 %d 毫秒内生成 Id。", lastTimestamp - timestamp));
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            // 当前毫秒内，则 +1
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        //时间戳改变，毫秒内序列重置
        else {
            sequence = 0;
        }

        //上次生成ID的时间截
        lastTimestamp = timestamp;

        //移位并通过或运算拼到一起组成64位的ID
        long nextId = ((timestamp - twepoch) << timestampLeftShift)
                | (dataCenterId << dataCenterIdLeftShift)
                | (workerId << workerIdLeftShift)
                | sequence;

        return nextId;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     * @return 当前时间(毫秒)
     */
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
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker();

        for (int i = 0; i < 10000; i++) {
            System.out.println(idWorker.nextId());
        }
    }
}
