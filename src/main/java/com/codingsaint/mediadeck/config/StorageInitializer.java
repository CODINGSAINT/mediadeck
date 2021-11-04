package com.codingsaint.mediadeck.config;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;


public class StorageInitializer {
    /* @Value("${mediadeck.firebase.stogaejson.path:C:\\Users\\07pal\\development\\storage.json}")
     private String storageJson;

     @Value("${mediadeck.firebase.projectId:}")
     private String projectId;*/


    private StorageInitializer() {

    }

    private static Storage storage;

    public static Storage storage(){
        return storage;
    }


    public static Storage storage(InputStream firebaseCredentialsStream,
                                  String prjId) throws IOException {

        Credentials credentials = GoogleCredentials
                .fromStream(firebaseCredentialsStream);
        storage = StorageOptions.newBuilder().setCredentials(credentials)
                .setProjectId(prjId).build().getService();
        return storage;
    }

}
