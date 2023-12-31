package com.game.server.service;

import com.game.server.entity.Level;
import com.game.server.repository.LevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LevelServiceImpl implements LevelService{

    private final LevelRepository levelRepository;

    @Override
    public List<Level> findAll() {
        return levelRepository.findAll();
    }

    @Override
    public void save(Level level) {
        levelRepository.save(level);
    }

    @Override
    public Level findByLevelName(String name) {
        return levelRepository.findByLevelName(name);
    }
}
