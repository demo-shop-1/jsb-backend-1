package org.demo.shop1.modules.products.adapters.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.mongodb.reactivestreams.client.MongoClient;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ReactiveMongoConfig {

    private final MongoClient mongoClient;

    @Value("${spring.data.mongodb.database}")
    private String dataBaseName;

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(mongoClient, dataBaseName);
    }

}
