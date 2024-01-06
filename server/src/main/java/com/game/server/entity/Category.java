package com.game.server.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;




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


    public Category(String categoryName, String symbol) {
        this.categoryName = categoryName;
        this.symbol = symbol;
    }


}
