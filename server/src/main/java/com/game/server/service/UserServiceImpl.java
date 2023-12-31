package com.game.server.service;

import com.game.server.entity.User;
import com.game.server.exception.UserNotFoundException;
import com.game.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean hasUserWithUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean hasUserWithEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User validateAndGetUserByUsername(String username) {
        if(getUserByUsername(username) == null) {
            throw new UserNotFoundException(String.format("User with username %s not found", username));
        } else {
            return getUserByUsername(username);
        }
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

}
