package com.game.server.service;


import com.game.server.entity.Item;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemService {

    List<Item> findAll();

    void save(Item item);

    Item findById(Long id);

    void deleteById(Long id);

    public void saveItemsToDatabase(MultipartFile file);

}
