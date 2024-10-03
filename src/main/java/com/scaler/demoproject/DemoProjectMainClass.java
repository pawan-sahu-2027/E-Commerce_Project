package com.scaler.demoproject;

import com.scaler.demoproject.model.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class DemoProjectMainClass  {
    public static void main(String[] args) {
        SpringApplication.run(DemoProjectMainClass.class, args);
        Product p = new Product();
        p.setTitle("shh");
    }
}
