package com.tripmate.common.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.RemovalListener;
import com.tripmate.domain.common.vo.CacheEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Configuration
public class CaffeineConfig {
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager manager = new SimpleCacheManager();

        List<CaffeineCache> caches = Arrays.stream(CacheEnum.values())
                                           .map(cacheEnum -> buildCache(cacheEnum.getCacheName(),
                                                                        cacheEnum.getMinutesToExpire(),
                                                                        cacheEnum.getTimeUnit()))
                                           .collect(Collectors.toList());
        manager.setCaches(caches);

        return manager;
    }

    private CaffeineCache buildCache(String cacheName, int minutesToExpire, TimeUnit timeUnit) {
        return new CaffeineCache(cacheName, Caffeine.newBuilder()
                                                    .expireAfterWrite(minutesToExpire, timeUnit)
                                                    .removalListener(new CustomRemovalListner())
                                                    .build());
    }

    @PreDestroy
    public void clearCache() {
        cacheManager().getCacheNames().forEach(cache -> cacheManager().getCache(cache).clear());
    }

    static class CustomRemovalListner implements RemovalListener<Object, Object> {
        @Override
        public void onRemoval(Object key, Object value, RemovalCause cause) {
            log.info("removal called with key {}, cause {}, evicted {} \n", key, cause.toString(), cause.wasEvicted());
        }
    }
}