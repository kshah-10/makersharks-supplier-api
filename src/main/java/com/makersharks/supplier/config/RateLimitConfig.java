package com.makersharks.supplier.config;

import com.giffing.bucket4j.spring.boot.starter.configuration.properties.Bucket4JConfiguration;
import com.giffing.bucket4j.spring.boot.starter.configuration.properties.Bucket4JConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RateLimitConfig {

    @Bean
    public Bucket4JConfigurationProperties bucket4JConfigurationProperties() {
        Bucket4JConfigurationProperties properties = new Bucket4JConfigurationProperties();
        Bucket4JConfiguration configuration = new Bucket4JConfiguration();
        configuration.setUrl("/api/supplier/query");
        configuration.setRateLimit(5L);
        configuration.setPeriodTime(1L);
        configuration.setPeriodUnit("MINUTES");
        properties.getBuckets().add(configuration);
        return properties;
    }
}