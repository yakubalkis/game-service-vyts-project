package com.game.server.controller;

import com.game.server.entity.Role;
import com.game.server.entity.User;
import com.game.server.request.UserRequest;
import com.game.server.response.AuthResponse;
import com.game.server.security.JwtTokenProvider;
import com.game.server.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody UserRequest userRequest) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword());

        Authentication auth = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(token);

        User user = userService.findByUsername(userRequest.getUsername());

        // set jwt and username to response
        AuthResponse authResponse = new AuthResponse();
        authResponse.setMessage("Bearer "+ jwtToken);
        authResponse.setUsername(user.getUsername());

        return authResponse;
    }

    @PostMapping("/register/{roleName}")
    public ResponseEntity<AuthResponse> register(@PathVariable String roleName, @RequestBody User user) {
        AuthResponse authResponse = new AuthResponse();
        Role role = null;

        // check if username is already existed
        if(userService.findByUsername(user.getUsername()) != null) {
            authResponse.setMessage("Username already in use");
            return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
        }

        // set role to laborant
        if(roleName.equals("admin")) {
            role = new Role("ROLE_ADMIN");
        } else if(roleName.equals("user")) {
            role = new Role("ROLE_USER");
        }
        user.setRole(role);

        // encode and set password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        authResponse.setMessage("Successfully registered. You can login.");

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }



}
