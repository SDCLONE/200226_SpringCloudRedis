package com.example.redisannotation.configuration;


import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;


import java.io.Serializable;
import java.lang.reflect.Method;
import java.time.Duration;

@Configuration
@EnableCaching      //使用这个来用注解使用缓存
public class RedisConfig {
    /*
    用来配置redisTemplate
    必须使用connectionFactory
    之后必须将model类序列化
     */
    @Bean
    public RedisTemplate<String, Serializable>
        redisTemplate(LettuceConnectionFactory connectionFactory){
        RedisTemplate<String,Serializable> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }

    //CacheManager用来设置缓存过期时间
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory){
        RedisCacheConfiguration cacheConfiguration=
                RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(Duration.ofMinutes(5))
                        .disableCachingNullValues()
                        .serializeValuesWith(
                                RedisSerializationContext.SerializationPair.fromSerializer(
                                        new GenericJackson2JsonRedisSerializer()
                                )
                            );
        return RedisCacheManager.builder(factory).cacheDefaults(cacheConfiguration).build();
    }

    //设置缓存key的生成方式   类名:方法名:参数
    @Bean
    public KeyGenerator keyGenerator(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... objects) {
                StringBuilder sb=new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(":").append(method.getName());
//                sb.append("hello");
                for (Object obj : objects) {
                    sb.append(":").append(obj.toString());
                }
                return sb.toString();
            }
        };
    }
}
