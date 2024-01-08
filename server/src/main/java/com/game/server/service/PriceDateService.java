package com.game.server.service;

import com.game.server.entity.PriceDate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PriceDateService {

    List<PriceDate> findAll();

    void savePriceDate(PriceDate priceDate);

    PriceDate findById(Long id);

    void deleteById(Long id);

    public void savePriceDatesToDatabase(MultipartFile file);

}
