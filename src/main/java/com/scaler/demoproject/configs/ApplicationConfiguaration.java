package com.scaler.demoproject.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Setter
@Getter
@Configuration // this class is my configuration class please create object of this while running

public class ApplicationConfiguaration {
    // we want to tell the spring that make object of it and store it in an application contest
    @Bean
    // Bean - retrurn the object of the applicatio contest
    public RestTemplate createRestTemplate(){

        return new RestTemplate();
    }
}
