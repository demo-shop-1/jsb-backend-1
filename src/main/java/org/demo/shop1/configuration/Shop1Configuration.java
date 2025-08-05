package org.demo.shop1.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.mongodb.reactivestreams.client.MongoClient;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class Shop1Configuration {

    private final MongoClient mongoClient;

    @Value("${spring.data.mongodb.database}")
    private String dataBaseName;

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(mongoClient, dataBaseName);
    }
}
