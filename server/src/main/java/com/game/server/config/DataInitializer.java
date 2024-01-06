package com.game.server.config;

import com.game.server.entity.*;
import com.game.server.repository.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class DataInitializer {

    private final LevelRepository levelRepository;
    private final RankRepository rankRepository;
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final SpecialityRepository specialityRepository;
    @PostConstruct
    public void createLevelAndRanks() {

        List<Level> levels = Arrays.asList(
                new Level("1", 100),
                new Level("2", 300),
                new Level("3", 500),
                new Level("4", 700),
                new Level("5", 900)
        );

        List<Rank> ranks = Arrays.asList(
                new Rank("Newbie", 100),
                new Rank("Junior", 500),
                new Rank("Mid", 1000),
                new Rank("Mid-Senior", 3000),
                new Rank("Senior", 7000)
        );

        /*levelRepository.saveAll(levels);
        rankRepository.saveAll(ranks);*/
    }
    @PostConstruct
    public void createCategories() {
        List<Category> categories = Arrays.asList(
                new Category("Forma", "formaCategory.png"),
                new Category("Krampon", "kramponCategory.png"),
                new Category("Eldiven", "eldivenCategory.png"),
                new Category("Sapka", "sapkaCategory.png")
        );

        //categoryRepository.saveAll(categories);
    }
    @PostConstruct
    public void createSpecialities() {
        List<Speciality> specialities = Arrays.asList(
                new Speciality("Falso", "Topa Vurulduğunda Falso Almasını Sağlar.","Falso.png",15),
                new Speciality("Sert Şut", "Topa Daha Sert Vurulmasını Sağlar.","SertSut.png",20)

        );

        //specialityRepository.saveAll(specialities);
    }

    @PostConstruct
    public void createItems() {
        List<Item> items = Arrays.asList(
                new Item("Forma", "forma.png"),
                new Item("Atesli Forma", "atesli-forma.png"),
                new Item("Krampon", "krampon.png"),
                new Item("Eldiven", "eldiven.png"),
                new Item("Sapka", "sapka.png")
        );
        //itemRepository.saveAll(items);
    }
}
