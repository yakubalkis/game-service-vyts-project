package com.game.server.service;

import com.game.server.entity.PriceDate;
import com.game.server.repository.PriceDateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PriceDateServiceImpl implements PriceDateService{

    private final PriceDateRepository priceDateRepository;

    @Override
    public List<PriceDate> findAll() {
        return priceDateRepository.findAll();
    }

    @Override
    public void savePriceDate(PriceDate item) {
        priceDateRepository.save(item);
    }

    @Override
    public PriceDate findById(Long id) {
        Optional<PriceDate> result = priceDateRepository.findById(id);
        PriceDate item = null;

        if(result.isPresent()) {
            item = result.get();
        } else {
            throw new RuntimeException("Did not found priceDate with id: "+ id);
        }

        return item;
    }
    //public List<PriceDate> findPriceDatesByItemId(Long id){return priceDateRepository.findPriceDatesByItemId(id);}
    @Override
    public void deleteById(Long id) {
        priceDateRepository.deleteById(id);
    }
}
