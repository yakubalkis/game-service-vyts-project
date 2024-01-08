package com.game.server.repository;

import com.game.server.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    // TO-DO: buraya itemin bütün price datelerini getiren method yazılacak
//her bir itemın ilk olarak item ismini, bu eşyaya sahip olan kullanıcı sayısını, bu eşyanın dahil olduğu envanter sayısını ve bu öğenin toplam satış sayısını gösterir.
    @Query("SELECT i.itemName, COUNT(DISTINCT u.id) AS userCount, COUNT(DISTINCT inv.id) AS inventoryCount, COUNT(DISTINCT p.id) AS totalSales " +
            "FROM Item i " +
            "LEFT JOIN i.inventories inv " +
            "LEFT JOIN inv.user u " +
            "LEFT JOIN i.purchases p " +
            "GROUP BY i.itemName")
    List<Object[]> getInventoryUserCountsGroupByItem();


}
