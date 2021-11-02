package com.codingsaint.mediadeck.service;

import com.codingsaint.mediadeck.config.StorageConfigProperties;
import com.codingsaint.mediadeck.config.StorageInitializer;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public final class FirebaseFileService implements FileService {
    private final StorageConfigProperties storageProperties;

    public FirebaseFileService( StorageConfigProperties storageProperties) {
        this.storageProperties = storageProperties;
    }

    @Override
    public byte[] download(String fileName) throws IOException {
        var blob =  StorageInitializer.storage().get(BlobId.of(storageProperties.getBucket(), fileName));
        return blob.getContent();
    }

    @Override
    public String upload(MultipartFile media) throws IOException {
        String fileName= RandomStringUtils.randomAlphanumeric(5).toLowerCase()+"_"+ media.getName();
        BlobId blobId = BlobId.of(storageProperties.getBucket(),fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        StorageInitializer.storage().create(blobInfo, media.getBytes());
        return fileName;
    }
}
