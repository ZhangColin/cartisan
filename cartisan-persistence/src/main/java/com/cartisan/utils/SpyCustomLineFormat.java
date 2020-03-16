package com.cartisan.utils;

import com.p6spy.engine.spy.appender.CustomLineFormat;

public class SpyCustomLineFormat extends CustomLineFormat {
    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        return super.formatMessage(connectionId, now, elapsed, category, prepared, sql, url)
                .replaceAll("\n", " ")
                .replaceAll("\\s+", " ");
    }
}
