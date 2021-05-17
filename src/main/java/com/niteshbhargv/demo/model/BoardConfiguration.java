package com.niteshbhargv.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties("board")
public class BoardConfiguration {

    int size;
    int snakes;
    int ladders;
    int start;
}
