/*
package com.codingsaint.mediadeck.endpoints;

import com.codingsaint.mediadeck.config.*;
import com.codingsaint.mediadeck.exceptions.MediaDeckExceptions;
import io.github.redouane59.twitter.TwitterClient;
import io.github.redouane59.twitter.signature.TwitterCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "*")
public class ConfigEndpoints  implements IConfigEndpoints{
       private final StorageConfigProperties storageProperties;
    private final FirebaseConfigParameters firebaseConfigParameters;
    public ConfigEndpoints(  StorageConfigProperties storageConfigProperties, FirebaseConfigParameters firebaseConfigParameters) {

        this.storageProperties = storageConfigProperties;
        this.firebaseConfigParameters = firebaseConfigParameters;
    }

    @Override
    public ResponseEntity<?> initFirebase(MultipartFile media, String databaseURL) throws IOException, MediaDeckExceptions {

        FireBaseInitializer.init(media.getBytes(), databaseURL);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> initStorage(MultipartFile media, String projectId, String bucket) throws IOException, MediaDeckExceptions {
        StorageInitializer.storage(storageProperties,media.getBytes(), projectId, bucket);

        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> twitterConfig(TwitterConfigProperties properties) throws IOException, MediaDeckExceptions {
        TwitterConfig.twitterClient(properties);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
*/
