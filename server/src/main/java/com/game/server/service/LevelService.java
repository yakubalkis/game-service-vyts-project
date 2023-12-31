package com.game.server.service;

import com.game.server.entity.Level;

import java.util.List;

public interface LevelService {
    List<Level> findAll();

    Level findById(Long id);

    void save(Level level);

    Level findByLevelName(String name);
}
