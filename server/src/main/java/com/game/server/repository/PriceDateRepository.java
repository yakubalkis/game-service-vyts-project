package com.game.server.repository;

import com.game.server.entity.PriceDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceDateRepository extends JpaRepository<PriceDate,Long> {

}
