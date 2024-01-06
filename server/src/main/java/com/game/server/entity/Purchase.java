package com.game.server.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int purchasePrice;

    private String purchaseDate;

    private int amount;

    private String priceType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Purchase(int purchasePrice, String purchaseDate,int amount,String priceType) {
        this.purchasePrice = purchasePrice;
        this.purchaseDate = purchaseDate;
        this.amount = amount;
        this.priceType = priceType;
    }
}
