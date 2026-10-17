package com.shammad.msbook.config.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "storage")
public class StorageProperties {

    private String provider;
    private String endpoint;
    private String region;
    private String accessKey;
    private String secretKey;
    private String bucketName;
    private long presignedUrlExpirationMinutes;
    private Paths paths;

    @Data
    public static class Paths {
        private String tempPosts = "temp/posts";
        private String posts = "posts";
        private String avatars = "avatars";
    }
}
