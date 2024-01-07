package com.game.server.entity;

import com.game.server.security.oauth2.AuthProvider;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "game_user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String phoneNumber;

    private String password;

    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String symbol;

    private String role;

    private String dateOfJoin;

    private Integer levelPointOfUser;

    private Integer rankPointOfUser;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}) // oyuncu silindiginde, level-rank tablolalari silinmesin
    @JoinColumn(name = "level_id")
    private Level level;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "rank_id")
    private Rank rank;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "budget_id")
    private Budget budget;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Log> logs;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Purchase> purchases;

    public User(String username, String password, String email, String phoneNumber, String role, String symbol, AuthProvider provider, String dateOfJoin) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.symbol = symbol;
        this.provider = provider;
        this.dateOfJoin = dateOfJoin;
    }

    public void addLog(Log log) {
        if(logs == null) {
            logs = new ArrayList<>();
        }
        logs.add(log);
        log.setUser(this);
    }

    public void addPurchase(Purchase purchase) {
        if (purchases == null) {
            purchases = new ArrayList<>();
        }
        purchases.add(purchase);
        purchase.setUser(this);
    }

}
