package com.game.server.repository;

import com.game.server.entity.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RankRepository extends JpaRepository<Rank, Long> {

    @Query("SELECT rank FROM Rank rank WHERE rank.rankName = ?1")
    Rank findRankByName(String name);
}
