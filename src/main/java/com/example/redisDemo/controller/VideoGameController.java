package com.example.redisDemo.controller;


import com.example.redisDemo.model.VideoGame;
import com.example.redisDemo.repository.VideoGameRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoGameController {

    @Autowired
    private VideoGameRepository videoGameRepository;

    @GetMapping("/games")
    Iterable<VideoGame> getAllVideoGames() {
        return videoGameRepository.findAll();
    }

    @GetMapping("/add")
    Success addVideoGames() {

        saveGamesToRedis();

        return new Success();
    }

    private void saveGamesToRedis() {
        videoGameRepository.save(
                VideoGame.builder()
                        .name("Call of Duty: Warzone")
                        .developer("Infinity Ward")
                        .publisher("Activision")
                        .genre("battle royale")
                        .build()
        );
        videoGameRepository.save(
                VideoGame.builder()
                        .name("Super Mario Odyssey")
                        .developer("Nintendo EPD")
                        .publisher("Nintendo")
                        .genre("action-adventure")
                        .build()
        );

        videoGameRepository.save(

                VideoGame.builder()
                        .name("FIFA")
                        .developer("EA Romania")
                        .publisher("EA Sports")
                        .genre("sports-simulation")
                        .build()
        );

        videoGameRepository.save(
                VideoGame.builder()
                        .name("Red Dead Redemption 2")
                        .developer("Rockstar Studios")
                        .publisher("Rockstar Games")
                        .genre("action-adventure")
                        .build()
        );
    }

    @Data
    class Success{
        String status = "Success";
    }

}
