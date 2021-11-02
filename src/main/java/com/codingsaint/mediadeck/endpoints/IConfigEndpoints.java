package com.codingsaint.mediadeck.endpoints;

import com.codingsaint.mediadeck.config.TwitterConfigProperties;
import com.codingsaint.mediadeck.exceptions.MediaDeckExceptions;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface IConfigEndpoints {
    @RequestMapping(path = "config/upload/firebase", method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> initFirebase(@RequestParam("file") MultipartFile media,
                                   @RequestHeader ("databaseURL") String databaseURL) throws IOException, MediaDeckExceptions;

    @RequestMapping(path = "config/upload/storage", method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> initStorage(@RequestParam("file") MultipartFile media, @RequestHeader ("projectId") String projectId,  @RequestHeader ("bucket") String bucket) throws IOException, MediaDeckExceptions;

    @PostMapping("config/twitter")
    ResponseEntity<?> twitterConfig(@RequestBody TwitterConfigProperties media) throws IOException, MediaDeckExceptions;


}
