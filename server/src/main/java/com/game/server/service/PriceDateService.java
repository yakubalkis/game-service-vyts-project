package com.game.server.service;

import com.game.server.entity.PriceDate;

import java.util.List;

public interface PriceDateService {

    List<PriceDate> findAll();

    void savePriceDate(PriceDate priceDate);

    PriceDate findById(Long id);

    //List<PriceDate> findPriceDatesByItemId(Long id);
    void deleteById(Long id);
}
