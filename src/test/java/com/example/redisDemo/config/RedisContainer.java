package com.example.redisDemo.config;

import org.junit.ClassRule;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@TestConfiguration
public class RedisContainer {

    @Container
    @ClassRule
    public GenericContainer redis = new GenericContainer("redis:5.0.3-alpine")
            .withExposedPorts(6379);


    @PostConstruct
    public void postConstruct() {
        System.out.println("> Starting Container");
        redis.start();
    }


    @PreDestroy
    public void preDestroy() {
        System.out.println("> Stopping Container");
        redis.stop();
    }


    @Bean
    LettuceConnectionFactory getLettuceConnectionFactory(){
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();

        config.setPort(redis.getFirstMappedPort());

        return new LettuceConnectionFactory(config);
    }
}