package com.example.redisDemo.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@Builder
@RedisHash("User")
public class VideoGame {
    @Id
    String id;
    String name;
    String developer;
    String publisher;
    String genre;
}
