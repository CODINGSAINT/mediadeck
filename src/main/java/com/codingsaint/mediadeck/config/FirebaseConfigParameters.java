package com.codingsaint.mediadeck.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Data
@ConfigurationProperties( "mediadeck.firebase")
public class FirebaseConfigParameters {
    /* File Path if present on disk*/
    private String filePath;
    /* Database URL for firebase */
    private String dbURL;
}
