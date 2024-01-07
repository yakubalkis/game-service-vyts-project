package com.game.server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
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


    public Speciality(String specialityName, String description, String symbol, int powerAmount) {
        this.specialityName = specialityName;
        this.description = description;
        this.symbol = symbol;
        this.powerAmount = powerAmount;
    }
}