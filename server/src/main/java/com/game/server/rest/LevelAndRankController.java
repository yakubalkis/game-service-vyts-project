package com.game.server.rest;

import com.game.server.entity.User;
import com.game.server.rest.dto.AuthResponse;
import com.game.server.rest.dto.RiseRequest;
import com.game.server.service.LevelService;
import com.game.server.service.RankService;
import com.game.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rise")
public class LevelAndRankController {

    private final RankService rankService;
    private final LevelService levelService;
    private final UserService userService;

    @PutMapping("/level/{username}")
    public AuthResponse changeLevel(@PathVariable String username, @RequestBody RiseRequest request) {
        User currentUser = userService.getUserByUsername(username);
        // rank ve level tablolari iliskisiz olmali mi,sadece min puan ve ad bilgisi icin kullanilmali mi?
        // user'da hangi attribute ile karsilastirma yapip level ve rank degistircez?

        return null;
    }

    @PutMapping("/rank/{username}")
    public AuthResponse changeRank(@PathVariable String username, @RequestBody RiseRequest request) {
        User currentUser = userService.getUserByUsername(username);

        return null;
    }

}
