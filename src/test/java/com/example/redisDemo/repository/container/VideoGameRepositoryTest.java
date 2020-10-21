package com.example.redisDemo.repository.container;

import com.example.redisDemo.config.RedisContainer;
import com.example.redisDemo.model.VideoGame;
import com.example.redisDemo.repository.VideoGameRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext
@DataRedisTest
@Import(RedisContainer.class)
@DisplayName("VideoGame Repository Container")
class VideoGameRepositoryTest {

    @Autowired
    VideoGameRepository repo;

    @Test
    @DisplayName("save and retrieve a video game from redis")
    void saveRetrieve() {
        assertThat(repo.findAll()).isEmpty();

        repo.saveAll(getListOfGames());


        List<VideoGame> gamesListFromRedis = Lists.newArrayList(repo.findAll());

        assertThat(gamesListFromRedis).hasSize(4);

        List<String> nameList = gamesListFromRedis.stream().map(VideoGame::getName).collect(Collectors.toList());
        assertThat(nameList).contains("Call of Duty: Warzone");
        assertThat(nameList).contains("Red Dead Redemption 2");
        assertThat(nameList).contains("FIFA");
        assertThat(nameList).contains("Super Mario Odyssey");
    }


    private List<VideoGame> getListOfGames() {
        return asList(
                VideoGame.builder()
                        .name("Call of Duty: Warzone")
                        .developer("Infinity Ward")
                        .publisher("Activision")
                        .genre("battle royale")
                        .build(),
                VideoGame.builder()
                        .name("Super Mario Odyssey")
                        .developer("Nintendo EPD")
                        .publisher("Nintendo")
                        .genre("action-adventure")
                        .build(),
                VideoGame.builder()
                        .name("FIFA")
                        .developer("EA Romania")
                        .publisher("EA Sports")
                        .genre("sports-simulation")
                        .build(),
                VideoGame.builder()
                        .name("Red Dead Redemption 2")
                        .developer("Rockstar Studios")
                        .publisher("Rockstar Games")
                        .genre("action-adventure")
                        .build()
        );
    }
}

