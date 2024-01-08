package com.game.server.repository;

import com.game.server.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    //her bir kullanıcının her bir alışveriş tarihinde hangi ürünleri kaç adet satın aldığını ve toplam kaç adet satın alma yapıldığını gösterir.
    Purchase findByUserId(Long id);
    @Query("SELECT u.username, p.purchaseDate, SUM(p.amount), i.itemName " +
            "FROM User u " +
            "JOIN u.purchases p " +
            "JOIN p.items i " +
            "WHERE u.id = :userId " +
            "GROUP BY u.username, p.purchaseDate, i.itemName " +
            "ORDER BY SUM(p.amount) DESC")
    List<Object[]> findTotalPurchaseDetailsByUserAndDate(@Param("userId") Long userId);

}
