package com.example.redisDemo.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.test.annotation.DirtiesContext;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@TestConfiguration
public class RedisEmbeddedServer {
    private final int port = 6379;
    private final RedisServer redisServer;

    RedisEmbeddedServer(){
        redisServer = new RedisServer(port);
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("> Starting Embedded Server");
        redisServer.start();
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("> Stopping Embedded Server");
        redisServer.stop();
    }

    @Bean
    LettuceConnectionFactory getLettuceConnectionFactory(){
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();

        config.setPort(port);

        return new LettuceConnectionFactory(config);
    }
}
