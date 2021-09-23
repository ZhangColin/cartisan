package com.cartisan.utils;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.Optional;

import static java.time.Clock.systemUTC;
import static java.time.ZoneOffset.UTC;

/**
 * @author colin
 */
public final class DateUtil {
    /**
     * 日期时间匹配格式
     */
    public interface Pattern{
        /**
         * 常规模式
         */
        String DATE = "yyyy-MM-dd";
        String DATETIME = "yyyy-MM-dd HH:mm:ss";
        String DATETIME_MM = "yyyy-MM-dd HH:mm";
        String DATETIME_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
        String TIME = "HH:mm";
        String TIME_SS = "HH:mm:ss";

        /**
         * 系统时间格式
         */
        String SYS_DATE = "yyyy/MM/dd";
        String SYS_DATETIME = "yyyy/MM/dd HH:mm:ss";
        String SYS_DATETIME_MM = "yyyy/MM/dd HH:mm";
        String SYS_DATETIME_SSS = "yyyy/MM/dd HH:mm:ss.SSS";

        /**
         * 无连接符模式
         */
        String NONE_DATE = "yyyyMMdd";
        String NONE_DATETIME = "yyyyMMddHHmmss";
        String NONE_DATETIME_MM = "yyyyMMddHHmm";
        String NONE_DATETIME_SSS = "yyyyMMddHHmmssSSS";
    }

    public static final String DEFAULT_PATTERN=Pattern.DATETIME;

    private static final String[] PARSE_PATTERNS = new String[]{
            Pattern.DATE,
            Pattern.DATETIME,
            Pattern.DATETIME_MM,
            Pattern.DATETIME_SSS,
            Pattern.SYS_DATE,
            Pattern.SYS_DATETIME,
            Pattern.SYS_DATETIME_MM,
            Pattern.SYS_DATETIME_SSS
    };

    private DateUtil() {
    }

    /**
     * 格式化日期
     * @param date 日期（时间）
     * @param pattern 匹配模式 参考：{@link DateUtil.Pattern}
     * @return 格式化后的字符串
     */
    public static String format(LocalDateTime date, String pattern) {
        if (date == null) {
            return null;
        }
        pattern= StringUtils.isNotBlank(pattern)?pattern:DEFAULT_PATTERN;
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 解析日期
     * @param date 日期（时间）
     * @return 解析后的日期，默认格式：yyyy-MM-dd HH:mm:ss
     */
    public static LocalDateTime parseDate(String date) {
        if (StringUtils.isBlank(date)) {
            return null;
        }

        return paseDate(date, DEFAULT_PATTERN);
    }

    /**
     * 解析日期
     * @param date 日期（时间）
     * @param pattern 匹配模式 参考：{@link DateUtil.Pattern}
     * @return 解析后的日期，默认格式：yyyy-MM-dd HH:mm:ss
     */
    public static LocalDateTime paseDate(String date, String pattern) {
        pattern= StringUtils.isNotBlank(pattern)?pattern:DEFAULT_PATTERN;
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime expireNowUtc(int time, TemporalUnit unit) {
        return nowUtc().plus(time, unit);
    }

    public static LocalDateTime nowUtc() {
        return LocalDateTime.now(systemUTC());
    }

    public static Date toDate(LocalDateTime localDateTime) {
        return Optional.ofNullable(localDateTime)
                .map(ldt -> ldt.toInstant(UTC))
                .map(Date::from)
                .orElse(null);
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return Optional.ofNullable(date)
                .map(Date::toInstant)
                .map(i -> i.atOffset(UTC))
                .map(OffsetDateTime::toLocalDateTime)
                .orElse(null);
    }


}
