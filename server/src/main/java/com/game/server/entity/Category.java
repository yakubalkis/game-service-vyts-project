package com.game.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryName;

    private String symbol;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Item>  items;


    public Category(String categoryName, String symbol) {
        this.categoryName = categoryName;
        this.symbol = symbol;
    }

    public void addItem(Item item) {
        if(items == null) {
            items = new ArrayList<>();
        }
        items.add(item);
        item.setCategory(this);
    }

}
