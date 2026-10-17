package com.shammad.msbook.config.mongo;

import org.springframework.boot.mongodb.autoconfigure.MongoClientSettingsBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClientSettingsBuilderCustomizer mongoClientSettingsBuilderCustomizer() {
        return builder -> builder.applyToConnectionPoolSettings(pool -> pool
                .minSize(0)
                .maxSize(20)
                .maxConnectionIdleTime(30, TimeUnit.SECONDS)
                .maxWaitTime(5, TimeUnit.SECONDS)
        );
    }
}
