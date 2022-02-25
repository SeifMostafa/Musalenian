package com.musala.MDS.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Profile("local")
public class AutoCreateConfig {

    @Bean
    public NewTopic registerHours() {
        return TopicBuilder
                .name("NEW_REGISTERATION")
                .partitions(3)
                .replicas(3).build();
    }
}
