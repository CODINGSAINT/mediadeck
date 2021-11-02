package com.codingsaint.mediadeck.endpoints;

import com.codingsaint.mediadeck.exceptions.MediaDeckExceptions;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileEndpoints {
    @RequestMapping(path = "upload", method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> upload(@RequestParam("file") MultipartFile media) throws IOException, MediaDeckExceptions;

    @RequestMapping(path = "download", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] download(@RequestParam("img") String fileName) throws IOException;
}
