package com.codingsaint.mediadeck.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class FireBaseInitializer {
    private FireBaseInitializer(){
    }

    public static void init(InputStream firebaseCredentialStream) throws Exception {
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(firebaseCredentialStream))
                .build();
        FirebaseApp.initializeApp(options);

    }

}