package com.game.server.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "speciality")
public class Speciality {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String specialityName;

    private String description;

    private String symbol;

    private int powerAmount;

    @ManyToMany
    @JoinTable(
            name = "speciality_item",
            joinColumns = @JoinColumn(name = "speciality_id"),
            inverseJoinColumns = @JoinColumn(name ="item_id")
    )
    private List<Item> items;


    public Speciality(String specialityName, String description, String symbol, int powerAmount) {
        this.specialityName = specialityName;
        this.description = description;
        this.symbol = symbol;
        this.powerAmount = powerAmount;
    }
}