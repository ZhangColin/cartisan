package com.cartisan.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.IntConsumer;
import java.util.function.Supplier;

/**
 * @author colin
 */
public class TraceWatch {
    /** Start time of the current task. */
    private long startMs;

    /** Name of the current task. */
    @Nullable
    private String currentTaskName;

    @Getter
    private final Map<String, List<TaskInfo>> taskMap = new HashMap<>();

    /**
     * 开始时间差类型指标记录，如果需要终止，请调用 {@link #stop()}
     *
     * @param taskName 指标名
     */
    public void start(String taskName) throws IllegalStateException {
        if (this.currentTaskName != null) {
            throw new IllegalStateException("Can't start TraceWatch: it's already running");
        }
        this.currentTaskName = taskName;
        this.startMs = TimeUtils.nowMs();
    }

    /**
     * 终止时间差类型指标记录，调用前请确保已经调用
     */
    public void stop() throws IllegalStateException {
        if (this.currentTaskName == null) {
            throw new IllegalStateException("Can't stop TraceWatch: it's not running");
        }
        long lastTime = TimeUtils.nowMs() - this.startMs;

        TaskInfo info = new TaskInfo(this.currentTaskName, lastTime);

        this.taskMap.computeIfAbsent(this.currentTaskName, e -> new LinkedList<>()).add(info);

        this.currentTaskName = null;
    }

    /**
     * 直接记录指标数据，不局限于时间差类型
     *  @param taskName 指标名
     * @param data 指标数据
     */
    public void record(String taskName, Object data) {
        TaskInfo info = new TaskInfo(taskName, data);

        this.taskMap.computeIfAbsent(taskName, e -> new LinkedList<>()).add(info);
    }

    /**
     * 有返回值调用
     */
    public static <T> T run(TraceWatch traceWatch, String taskName, Supplier<T> supplier) {
        try {
            traceWatch.start(taskName);

            return supplier.get();
        } finally {
            traceWatch.stop();
        }
    }

    /**
     * 无返回值调用
     */
    public static void run(TraceWatch traceWatch, String taskName, IntConsumer function) {
        try {
            traceWatch.start(taskName);

            function.accept(0);
        } finally {
            traceWatch.stop();
        }
    }

    @Getter
    @AllArgsConstructor
    public static final class TaskInfo {
        private final String taskName;

        private final Object data;
    }
}
