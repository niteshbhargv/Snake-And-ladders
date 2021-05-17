package com.niteshbhargv.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("city")
public class CityConfiguration {

    int division;
    String state;
}
