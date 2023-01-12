package com.tripmate.domain.common.vo;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public enum CacheEnum {
    CACHE_TIME_1MINUTE("1min", 1, TimeUnit.MINUTES),
    CACHE_TIME_5MINUTE("5min", 5, TimeUnit.MINUTES),
    CACHE_TIME_10MINUTE("10min", 10, TimeUnit.MINUTES),
    CACHE_TIME_30MINUTE("30min", 30, TimeUnit.MINUTES),
    CACHE_TIME_1HOUR("1hour", 1, TimeUnit.HOURS);

    private final String cacheName;
    private final int minutesToExpire;
    private final TimeUnit timeUnit;

    public String getCacheName() {
        return cacheName;
    }

    public int getMinutesToExpire() {
        return minutesToExpire;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }
}