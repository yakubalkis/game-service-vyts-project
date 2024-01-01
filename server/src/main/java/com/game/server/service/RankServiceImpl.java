package com.game.server.service;

import com.game.server.entity.Rank;
import com.game.server.repository.RankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RankServiceImpl implements RankService {

    private final RankRepository rankRepository;
    @Override
    public List<Rank> findAll() {
        return rankRepository.findAll();
    }

    @Override
    public Rank findById(Long id) {
        Optional<Rank> result = rankRepository.findById(id);
        Rank rank = null;
        if(result.isPresent()) {
            rank = result.get();
        } else {
            throw new RuntimeException("Did not found item with id: "+ id);
        }

        return rank;
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
