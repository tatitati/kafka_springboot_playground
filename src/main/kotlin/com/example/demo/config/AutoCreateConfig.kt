package com.example.demo.config

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder

@Configuration
class AutoCreateConfig {

    @Bean
    fun createNewTopic(): NewTopic {
       return TopicBuilder
                .name("library-events")
                .partitions(3)
                .replicas(3)
                .build()
    }
}