package com.game.server.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @ManyToOne
    @JoinColumn(name = "category_id")
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


    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<PriceDate>priceDates;


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

    public void addPriceDate(PriceDate priceDate) { // simdilik gerekli degil ama dursun
        if(priceDates == null) {
            priceDates = new ArrayList<>();
        }
        priceDates.add(priceDate);
        priceDate.setItem(this);
    }

    // trigger yada advance sorgu kullanilabilir, birden cok priceDate var ve güncel tarih icin gecerli olan gelmeli
    public PriceDate getCurrentPriceDate() { // bu method PriceDateRepository'de de yazilabilir sql query method ile
                                            // methodun burada olmasi sart degil sadece hatirlatma icin
        return null;
    }

}
