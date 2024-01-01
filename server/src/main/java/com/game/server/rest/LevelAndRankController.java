package com.game.server.rest;

import com.game.server.entity.Level;
import com.game.server.entity.Rank;
import com.game.server.entity.User;
import com.game.server.rest.dto.RiseRequest;
import com.game.server.service.LevelService;
import com.game.server.service.RankService;
import com.game.server.service.UserService;
import lombok.RequiredArgsConstructor;
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

    @PutMapping("/rank")
    public String changeRank(@RequestBody RiseRequest request) {
        User currentUser = userService.getUserByUsername(request.getUsername());
        if(request.getProcessType().equals("increase")) { // TO-DO: bu kisim yapilacak

        }
        else if(request.getProcessType().equals("decrease")) {

        }

        return null;
    }

    @PutMapping("/level")
    public String changeLevel(@RequestBody RiseRequest request) {
        User user = userService.getUserByUsername(request.getUsername());
        Level currentLevel = levelService.findByLevelName(user.getLevel().getLevelName());

        if(request.getProcessType().equals("increase")) { // level veya point arttir
            Integer addedPoint = user.getLevelPointOfUser() + request.getPoint();

            if(currentLevel.getId() != 5) { // Buradaki 5, level tablosundaki son levelin id si olacak!
                Level nextLevel = levelService.findById(currentLevel.getId()+1);

                if(addedPoint >= nextLevel.getMinPoint()) { // level attir
                    user.setLevel(nextLevel);
                    user.setLevelPointOfUser(addedPoint);
                    userService.saveUser(user);
                    return "Level increased, congratulations!"; // loglansin bu returnden önce, seviye atladi cünkü
                }
            }
            user.setLevelPointOfUser(addedPoint); // seviye atlama yok, puani ekle kaydet
            userService.saveUser(user);
        }
        else if(request.getProcessType().equals("decrease")) { // level veya point azalt
            Integer substractedPoint = user.getLevelPointOfUser() - request.getPoint();

            if(currentLevel.getId() != 1) { // id 1 den öncesi yok o yüzden check
                Level previousLevel = levelService.findById(currentLevel.getId()-1);
                if(substractedPoint < currentLevel.getMinPoint()) {
                    user.setLevel(previousLevel);
                    user.setLevelPointOfUser(substractedPoint);
                    userService.saveUser(user);
                    return "Level decreased, play hard!"; // loglansin bu returnden önce, seviye düstü cünkü
                }
            }
            if(substractedPoint >= 100) { // seviye düsme yok, puani ekle kaydet
                user.setLevelPointOfUser(substractedPoint);
            } else { // puan 100 den asagi düsmemeli o zaten baslangic puani
                user.setLevelPointOfUser(100);
            }
            userService.saveUser(user);
        }

        return "Changed level point.";
    }

}
