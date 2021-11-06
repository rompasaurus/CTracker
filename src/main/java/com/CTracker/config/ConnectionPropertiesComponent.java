package com.CTracker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConnectionPropertiesComponent {

    @Value("${spring.allowed.endpoint}")
    public String allowedEndpoint;

    @Value("${spring.allowed.origin}")
    public String allowedOrigin;

    @Value("${spring.allowed.subnet}")
    public String allowedOriginSubnet;
}