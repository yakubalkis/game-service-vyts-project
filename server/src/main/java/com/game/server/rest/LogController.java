package com.game.server.rest;

import com.game.server.entity.Log;
import com.game.server.entity.User;
import com.game.server.repository.LogRepository;
import com.game.server.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/logs")
@AllArgsConstructor
public class LogController {

    private final LogRepository logRepository;
    private final UserService userService;

    @GetMapping("")
    public List<Log> getAllLogs() {
        return logRepository.findAll();
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getLogsOfUser(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        if(user == null) {
            return new ResponseEntity<>("User not found! Try again.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user.getLogs(), HttpStatus.OK) ;
    }

}
