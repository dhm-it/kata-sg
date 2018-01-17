package com.dhm.bank.databaseConfig;

import com.dhm.bank.envConfig.AppPropperties;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@Configuration
public class MongoConifg {
    @Autowired
    AppPropperties appPropperties;

    public @Bean
    MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(new MongoClient(), appPropperties.getDbName());
    }

    public @Bean
    MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongtemplate = new MongoTemplate(mongoDbFactory());
        return mongtemplate;
    }
}
