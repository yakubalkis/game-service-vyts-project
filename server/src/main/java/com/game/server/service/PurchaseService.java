package com.game.server.service;

import com.game.server.entity.Level;
import com.game.server.entity.Purchase;

import java.util.List;

public interface PurchaseService {
    List<Purchase> findAll();

    Purchase findById(Long id);

    void save(Purchase purchase);


}
