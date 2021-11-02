package com.codingsaint.mediadeck.config;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


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


    public static Storage storage(StorageConfigProperties storageProperties,
                                  byte[] file,
                                  String prjId,
                                  String bucket) throws IOException {
        storageProperties.setBucket(bucket);
        storageProperties.setProjectId(prjId);
        Credentials credentials = GoogleCredentials
                .fromStream(new ByteArrayInputStream(file));
        storage = StorageOptions.newBuilder().setCredentials(credentials)
                .setProjectId(prjId).build().getService();
        return storage;
    }

    public static Storage storage(StorageConfigProperties storageProperties
                                  ) throws IOException {

        Credentials credentials = GoogleCredentials
                .fromStream(new FileInputStream(storageProperties.getFilePath())) ;
        storage = StorageOptions.newBuilder().setCredentials(credentials)
                .setProjectId(storageProperties.getProjectId()).build().getService();
        return storage;
    }

}
