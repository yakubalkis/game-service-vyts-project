package com.game.server.repository;

import com.game.server.entity.PriceDate;
import com.game.server.entity.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceDateRepository extends JpaRepository<PriceDate,Long> {
    //@Query("SELECT priceDate FROM PriceDate priceDate WHERE PriceDate.item.itemName = ?1")
    //List<PriceDate> findPriceDatesByItemId(Long id);
}
