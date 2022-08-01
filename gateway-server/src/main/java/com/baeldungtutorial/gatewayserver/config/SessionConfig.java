package com.baeldungtutorial.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.session.FlushMode;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

@Configuration
@EnableRedisHttpSession(flushMode = FlushMode.IMMEDIATE)
public class SessionConfig extends AbstractHttpSessionApplicationInitializer {

    @Bean
    public RedisIndexedSessionRepository redisIndexedSessionRepository(RedisTemplate<Object, Object> redisTemplate)
    {
        return new RedisIndexedSessionRepository(redisTemplate);
    }
}
