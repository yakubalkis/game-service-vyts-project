package com.game.server.service;

import com.game.server.entity.Rank;

import java.util.List;

public interface RankService {

    List<Rank> findAll();

    Rank findById(Long id);

    void save(Rank rank);

    Rank findByRankName(String name);
}
