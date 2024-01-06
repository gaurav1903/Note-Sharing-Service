package com.example.RateLimiter.configs;

import com.example.RateLimiter.constants.ApplicationConstants;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RateLimiterConfig {

    @Bean
    public RateLimiter rateLimiter() {
        return RateLimiter.create(ApplicationConstants.requestLimit);
    }
}
