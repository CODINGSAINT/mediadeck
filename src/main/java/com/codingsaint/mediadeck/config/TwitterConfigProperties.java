package com.codingsaint.mediadeck.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties( "mediadeck.twiter.api")
public class TwitterConfigProperties {
    private  String key;
    private  String secretkey;
    private String accessToken;
    private  String accessTokenSecret;
}
