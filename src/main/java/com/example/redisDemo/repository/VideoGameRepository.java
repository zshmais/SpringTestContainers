package com.example.redisDemo.repository;

import com.example.redisDemo.model.VideoGame;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoGameRepository extends CrudRepository<VideoGame, String> {
}
