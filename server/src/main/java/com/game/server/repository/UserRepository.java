package com.game.server.repository;

import com.game.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT user FROM User user WHERE user.username = ?1")
    User findByUsername(String username);

    @Query("SELECT user FROM User user WHERE user.email = ?1")
    User findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
