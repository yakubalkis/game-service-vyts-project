package com.game.server.service;

import com.game.server.entity.User;
import com.game.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findById(Long id) {
        Optional<User> result = repository.findById(id);
        User user = null;

        if(result.isPresent()) {
            user = result.get();
        } else {
            throw new RuntimeException("Did not found report with id: "+ id);
        }

        return user;
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public User findByUsername(String id) {
        return repository.findUserByUsername(id);
    }
}
