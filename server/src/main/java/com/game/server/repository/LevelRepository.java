package com.game.server.repository;

import com.game.server.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
    @Query("Select lev from Level lev where lev.levelName = ?1")
    Level findByLevelName(String name);
}
