package com.example.redisannotation.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
利用这个类来进行redis异常挂掉处理
 */
@Configuration
public class CacheAutoConfiguration extends CachingConfigurerSupport {
    private Logger logger= LoggerFactory.getLogger(CacheAutoConfiguration.class);

    //用来解决缓存异常情况
    //当缓存失效时，整个程序不应当挂掉
    @Bean
    public CacheErrorHandler errorHandler(){
        CacheErrorHandler cacheErrorHandler=new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException e, Cache cache, Object o) {
                logger.error("redis异常",e);
            }

            @Override
            public void handleCachePutError(RuntimeException e, Cache cache, Object o, Object o1) {
                logger.error("redis异常",e);
            }

            @Override
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object o) {
                logger.error("redis异常",e);
            }

            @Override
            public void handleCacheClearError(RuntimeException e, Cache cache) {
                logger.error("redis异常",e);
            }
        };
        return cacheErrorHandler;
    }
}
