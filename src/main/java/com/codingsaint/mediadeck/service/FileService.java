package com.codingsaint.mediadeck.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public sealed interface FileService permits FirebaseFileService {
    public byte[] download(String fileName) throws IOException;
    public String upload(MultipartFile media) throws IOException;

}
