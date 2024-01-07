package com.game.server.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "priceDate")
public class PriceDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int price;

    private String priceDate;

    private String priceType;

    @ManyToOne
    @JoinColumn(name = "item")
    private Item item;
}
