package com.scaler.demoproject.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguaration {
    @Bean
    public RestTemplate createRestTemplate(){
        return new RestTemplate();
    }
}
