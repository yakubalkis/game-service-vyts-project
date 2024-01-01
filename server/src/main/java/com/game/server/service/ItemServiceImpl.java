package com.game.server.service;

import com.game.server.entity.Item;
import com.game.server.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public void save(Item item) {
        itemRepository.save(item);
    }

    @Override
    public Item findById(Long id) {
        Optional<Item> result = itemRepository.findById(id);
        Item item = null;

        if(result.isPresent()) {
            item = result.get();
        } else {
            throw new RuntimeException("Did not found item with id: "+ id);
        }

        return item;
    }

    @Override
    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }
}
