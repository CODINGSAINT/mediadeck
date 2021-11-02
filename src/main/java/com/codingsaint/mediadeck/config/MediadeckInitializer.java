package com.codingsaint.mediadeck.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class MediadeckInitializer implements CommandLineRunner {
    private final FirebaseConfigParameters firebaseParameters;
    private final TwitterConfigProperties twitterConfigProperties;
    private final StorageConfigProperties storageConfigProperties;

    public MediadeckInitializer(FirebaseConfigParameters firebaseParameters,
                                TwitterConfigProperties twitterConfigProperties,
                                StorageConfigProperties storageConfigProperties) {
        this.firebaseParameters = firebaseParameters;
        this.twitterConfigProperties = twitterConfigProperties;
        this.storageConfigProperties = storageConfigProperties;
    }


    private static final Logger LOG = LoggerFactory.getLogger(MediadeckInitializer.class);

    @Override
    public void run(String... args) throws Exception {

    }

    void firebase() throws Exception {
        File file = null;
        if (StringUtils.isNotEmpty(firebaseParameters.getFilePath()) &&
                StringUtils.isNotEmpty(firebaseParameters.getDbURL())
        ) {
            file = new File(firebaseParameters.getFilePath().trim());
            FireBaseInitializer.init(file, firebaseParameters.getDbURL());

        } else {
            LOG.error("Firebase is not setup , It will be required to setup firebase after startup");
        }
    }

    void storage() throws IOException {
        if (StringUtils.isNotEmpty(storageConfigProperties.getFilePath())
                && StringUtils.isNotEmpty(storageConfigProperties.getBucket())
                && StringUtils.isNotEmpty(storageConfigProperties.getProjectId())) {
            StorageInitializer.storage(storageConfigProperties);
        } else {
            LOG.error("Storage is not setup , It will be required to setup storage after startup");
        }

    }

    void twitter() {
        if (StringUtils.isNotEmpty(twitterConfigProperties.getAccessToken())
                && StringUtils.isNotEmpty(twitterConfigProperties.getKey())
                && StringUtils.isNotEmpty(twitterConfigProperties.getAccessTokenSecret())
                && StringUtils.isNotEmpty(twitterConfigProperties.getAccessToken())

        ) {
            TwitterConfig.twitterClient(twitterConfigProperties);
        } else {
            LOG.error("Twitter is not setup , It will be required to setup storage after startup");
        }
    }
}

