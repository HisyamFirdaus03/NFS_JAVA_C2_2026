package com.example.assettracker.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

/*
 * MongoConfig
 * -----------
 * Spring Boot 4.x's auto-configuration was not applying the credentials from
 * spring.data.mongodb.uri (credential came out null, so authenticated MongoDB
 * rejected every command). We build the MongoClient explicitly from the URI so
 * the username/password/authSource in the connection string are honoured, and
 * we pin the database factory to support_desk_db so operations run against the
 * database the app user is authorised for.
 */
@Configuration
public class MongoConfig {

    @Bean
    MongoClient mongoClient(@Value("${spring.data.mongodb.uri}") String uri) {
        return MongoClients.create(uri);
    }

    @Bean
    MongoDatabaseFactory mongoDatabaseFactory(MongoClient mongoClient,
                                              @Value("${spring.data.mongodb.database}") String database) {
        return new SimpleMongoClientDatabaseFactory(mongoClient, database);
    }
}
