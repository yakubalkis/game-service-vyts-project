package com.game.server.service;

import com.game.server.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    User save(User user);

    void deleteById(Long id);

    User findByUsername(String id);
}
