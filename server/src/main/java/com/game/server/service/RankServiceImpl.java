package com.game.server.service;

import com.game.server.entity.Rank;
import com.game.server.repository.RankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RankServiceImpl implements RankService{

    private final RankRepository rankRepository;
    @Override
    public List<Rank> findAll() {
        return rankRepository.findAll();
    }

    @Override
    public void save(Rank rank) {
        rankRepository.save(rank);
    }

    @Override
    public Rank findByRankName(String name) {
        return rankRepository.findRankByName(name);
    }
}
