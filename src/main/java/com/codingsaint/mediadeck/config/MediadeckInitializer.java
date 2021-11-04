package com.codingsaint.mediadeck.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class MediadeckInitializer implements CommandLineRunner {
    private final TwitterConfigProperties twitterConfigProperties;
    private final StorageConfigProperties storageConfigProperties;
    private final FirebaseCredentials firebaseCredentials;

    public MediadeckInitializer(
                                TwitterConfigProperties twitterConfigProperties,
                                StorageConfigProperties storageConfigProperties, FirebaseCredentials firebaseCredentials) {
        this.twitterConfigProperties = twitterConfigProperties;
        this.storageConfigProperties = storageConfigProperties;
        this.firebaseCredentials = firebaseCredentials;
    }


    private static final Logger LOG = LoggerFactory.getLogger(MediadeckInitializer.class);

    @Override
    public void run(String... args) throws Exception {
        firebase();
        storage();
        twitter();
    }

    void firebase() throws Exception {
        FireBaseInitializer.init(firebaseCredentialByteStream());
    }
    public  InputStream firebaseCredentialByteStream() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(firebaseCredentials);
        return  new ByteArrayInputStream(jsonString.getBytes());
    }
    void storage() throws Exception {
        StorageInitializer.storage(firebaseCredentialByteStream(),storageConfigProperties.getProjectId());
    }

    void twitter() {
            TwitterConfig.twitterClient(twitterConfigProperties);
     }
}

