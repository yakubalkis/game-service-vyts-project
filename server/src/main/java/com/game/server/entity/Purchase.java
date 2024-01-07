package com.game.server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany
    @JoinTable(
            name = "purchase_item",
            joinColumns = @JoinColumn(name = "purchase_id"),
            inverseJoinColumns = @JoinColumn(name ="item_id")
    )
    private List<Item> items;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public void addItem(Item item) {
        if(items == null) {
            items = new ArrayList<>();
        }
        items.add(item);
    }
    public void deleteItem(Item item) {
        if(items != null) {
            items.remove(item);
        }
    }
    public Purchase(int purchasePrice, String purchaseDate,int amount,String priceType) {
        this.purchasePrice = purchasePrice;
        this.purchaseDate = purchaseDate;
        this.amount = amount;
        this.priceType = priceType;
    }
}
