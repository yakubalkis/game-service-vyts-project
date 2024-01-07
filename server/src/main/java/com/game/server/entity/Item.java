package com.game.server.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;

    private String symbol;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "inventory_item",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name ="inventory_id")
    )
    private List<Inventory> inventories;

    @ManyToMany
    @JoinTable(
            name = "purchase_item",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name ="purchase_id")
    )
    private List<Purchase> purchases;

    public Item(String itemName, String symbol) {
        this.itemName = itemName;
        this.symbol = symbol;

    }

    public void addInventory(Inventory inventory) { // simdilik gerekli degil ama dursun
        if(inventories == null) {
            inventories = new ArrayList<>();
        }
        inventories.add(inventory);
    }

}
