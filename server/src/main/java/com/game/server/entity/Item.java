package com.game.server.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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

    @ManyToMany
    @JoinTable(
            name = "inventory_item",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name ="inventory_id")
    )
    private List<Inventory> inventories;

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
