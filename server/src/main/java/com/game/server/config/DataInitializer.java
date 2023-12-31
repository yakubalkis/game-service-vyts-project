package com.game.server.config;

import com.game.server.entity.Level;
import com.game.server.entity.Rank;
import com.game.server.repository.LevelRepository;
import com.game.server.repository.RankRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class DataInitializer {

    private final LevelRepository levelRepository;
    private final RankRepository rankRepository;

    @PostConstruct
    public void createLevelAndRanks() {

        List<Level> levels = Arrays.asList(
                new Level("1", 100),
                new Level("2", 300),
                new Level("3", 500),
                new Level("4", 700),
                new Level("5", 900)
        );

        List<Rank> ranks = Arrays.asList(
                new Rank("Newbie", 100),
                new Rank("Junior", 500),
                new Rank("Mid", 1000),
                new Rank("Mid-Senior", 3000),
                new Rank("Senior", 7000)
        );

        levelRepository.saveAll(levels);
        rankRepository.saveAll(ranks);
    }
}
