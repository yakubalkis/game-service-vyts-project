package com.game.server.service;

import com.game.server.entity.Item;
import com.game.server.entity.Purchase;
import com.game.server.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    @Override
    public List<Purchase> findAll() {
        return purchaseRepository.findAll();
    }

    @Override
    public Purchase findById(Long id) {
        Optional<Purchase> result = purchaseRepository.findById(id);
        Purchase purchase = null;
        if(result.isPresent()) {
            purchase = result.get();
        } else {
            throw new RuntimeException("Did not found purchase with id: "+ id);
        }

        return purchase;
    }
    @Override
    public void savePurchase(Purchase purchase) {
        purchaseRepository.save(purchase);
    }
}
