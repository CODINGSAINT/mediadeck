package com.codingsaint.mediadeck.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties("mediadeck.storage")
public class StorageConfigProperties {
    private String bucket;
    private String filePath;
    private String projectId;
}
