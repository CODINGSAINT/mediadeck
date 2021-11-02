package com.codingsaint.mediadeck.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;

public class FireBaseInitializer {
    private FireBaseInitializer(){
    }

    public static void init(byte[] file, String databaseURL) throws IOException {
        var serviceAccount =
                new ByteArrayInputStream(file);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl(databaseURL)
                .build();
        FirebaseApp.initializeApp(options);

    }

    public static void init(File file, String databaseURL) throws IOException {
        var serviceAccount =
                new FileInputStream(file);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl(databaseURL)
                .build();
        FirebaseApp.initializeApp(options);

    }
}