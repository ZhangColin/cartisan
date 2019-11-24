package com.cartisan.common.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * 获取真实IP
 *
 * @author colin
 */
public class IpUtil {
    private static final String N255 = "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
    private static final Pattern PATTERN = Pattern.compile("^(?:" + N255 + "\\.){3}" + N255 + "$");
    private static final String X_FORWARDED_FOR = "x-forwarded-for";
    private static final String PROXY_CLIENT_IP = "Proxy-Client-IP";
    private static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
    private static final String UNKNOWN = "unknown";

    private IpUtil() {
    }

    private static String longToIpV4(long longIp) {
        final long octet3 = (longIp >> 24) % 256;
        final long octet2 = (longIp >> 16) % 256;
        final long octet1 = (longIp >> 8) % 256;
        final long octet0 = longIp % 256;

        return octet3 + "." + octet2 + "." + octet1 + "." + octet0;
    }

    private static long ipV4ToLong(String ip) {
        final String[] octets = ip.split("\\.");

        return Long.parseLong(octets[0]) << 24
                + Long.parseLong(octets[1]) << 16
                + Long.parseLong(octets[2]) << 8
                + Long.parseLong(octets[3]);
    }

    private static boolean isIpV4Private(String ip) {
        long longIp = ipV4ToLong(ip);
        return (longIp >= ipV4ToLong("10.0.0.0") && longIp <= ipV4ToLong("10.255.255.255"))
                || (longIp >= ipV4ToLong("172.16.0.0") && longIp <= ipV4ToLong("172.31.255.255"))
                || (longIp >= ipV4ToLong("192.168.0.0") && longIp <= ipV4ToLong("192.168.255.255"));
    }

    private static boolean isIpV4Valid(String ip) {
        return PATTERN.matcher(ip).matches();
    }

    public static String getIpFromRequest(HttpServletRequest request) {
        String ip = request.getHeader(X_FORWARDED_FOR);
        if(ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(PROXY_CLIENT_IP);
        }
        if(ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(WL_PROXY_CLIENT_IP);
        }
        boolean found = false;
        if (ip != null) {
            final StringTokenizer tokenizer = new StringTokenizer(ip, ",");
            while (tokenizer.hasMoreElements()) {
                ip = tokenizer.nextToken().trim();
                if (isIpV4Valid(ip) && !isIpV4Private(ip)) {
                    found = true;
                    break;
                }
            }
        }
        if (!found) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }
}
