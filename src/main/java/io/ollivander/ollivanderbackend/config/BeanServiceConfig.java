package io.ollivander.ollivanderbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServiceConfig {
    @Bean(CORSFilter.CUSTOM_CORS_FILTER)
    public CORSFilter corsFilter() {
        return new  CORSFilter();
    }
}
