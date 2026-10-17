package com.shammad.msbook.config.minio;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.HeadBucketRequest;
import software.amazon.awssdk.services.s3.model.NoSuchBucketException;

@Slf4j
@Component
@RequiredArgsConstructor
public class StorageInitializer {

    private final S3Client s3Client;
    private final StorageProperties properties;

    @PostConstruct
    public void initBucket() {
        String bucket = properties.getBucketName();
        try {
            s3Client.headBucket(HeadBucketRequest.builder().bucket(bucket).build());
            log.info("S3/MinIO bucket '{}' already exists.", bucket);
        } catch (NoSuchBucketException e) {
            log.info("S3/MinIO bucket '{}' does not exist. Creating bucket...", bucket);
            s3Client.createBucket(CreateBucketRequest.builder().bucket(bucket).build());
            log.info("S3/MinIO bucket '{}' created successfully.", bucket);
        } catch (Exception e) {
            log.error("Failed to verify/create S3/MinIO bucket '{}': {}", bucket, e.getMessage());
        }
    }
}
