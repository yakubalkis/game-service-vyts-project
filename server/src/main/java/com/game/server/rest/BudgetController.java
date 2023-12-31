package com.game.server.rest;

import com.game.server.entity.User;
import com.game.server.rest.dto.BudgetRequest;
import com.game.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/budget")
public class BudgetController {

    private final UserService userService;

    @PutMapping("/{username}")
    String updateBudget(@PathVariable String username, @RequestBody BudgetRequest request) {
        User currentUser = userService.getUserByUsername(username);
        Integer currentGameMoney = currentUser.getBudget().getCurrentGameMoney();

        if(!(request.getAmount() instanceof Integer)) { // check variable if integer or not
            throw new RuntimeException("Value is not Integer!");
        }

        if(request.getProcessType().equals("increase")) {
            currentUser.getBudget().setCurrentGameMoney(currentGameMoney + request.getAmount());
        }
        else if(request.getProcessType().equals("decrease")) {
            if(currentGameMoney - request.getAmount() < 0) {
                throw new RuntimeException("Budget Limit isn't enough for this process!");
            } else {
                currentUser.getBudget().setCurrentGameMoney(currentGameMoney - request.getAmount());
            }
        }
        userService.saveUser(currentUser);
        return "Process is successful";
    }
}
