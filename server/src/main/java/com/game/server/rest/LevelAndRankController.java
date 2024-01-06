package com.game.server.rest;

import com.game.server.entity.Level;
import com.game.server.entity.Log;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        Rank currentRank=rankService.findByRankName(currentUser.getRank().getRankName());
        if(request.getProcessType().equals("increase")) { // rank veya point arttır
            Integer addedPoint=currentUser.getRankPointOfUser()+ request.getPoint();

            if(currentRank.getId() !=5) {// Buradaki 5, rank tablosundaki son rankın id si olacak!
                Rank nextRank=rankService.findById(currentRank.getId()+1);

                if(addedPoint >= nextRank.getMinPoint()) { //rank arttır
                    currentUser.setRank(nextRank);
                    currentUser.setRankPointOfUser(addedPoint);
                    userService.saveUser(currentUser);
                    //log
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String formattedDateTime = now.format(formatter);
                    String message = "User " + currentUser.getUsername() + " rank up to " + nextRank.getRankName() + " on " + formattedDateTime + ".";
                    Log log = new Log(message,"auth");
                    currentUser.addLog(log);// rank atladı loglandı
                    userService.saveUser(currentUser);

                    return "Rank increased, congratulations!";
                }
            }
            currentUser.setRankPointOfUser(addedPoint); // rank atlama yok, puani ekle kaydet
            userService.saveUser(currentUser);
        }
        else if(request.getProcessType().equals("decrease")) {// rank veya point düşür
            Integer subtractedPoint =currentUser.getRankPointOfUser()- request.getPoint();

            if(currentRank.getId() !=1) {//Buradaki 1, rank tablosundaki ilk rankın id si olacak!
                Rank previousRank=rankService.findById(currentRank.getId()-1);
                if(subtractedPoint<currentRank.getMinPoint()) {
                    currentUser.setRank(previousRank);
                    currentUser.setRankPointOfUser(subtractedPoint);
                    userService.saveUser(currentUser);
                    //log
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String formattedDateTime = now.format(formatter);
                    String message = "User " + currentUser.getUsername() + " rank low to " + previousRank.getRankName() + " on " + formattedDateTime + ".";
                    Log log = new Log(message,"auth");
                    currentUser.addLog(log);// rank düştü loglandı
                    userService.saveUser(currentUser);
                    return "Rank decreased, play hard!";
                }
            }
            if(subtractedPoint >= 100) { // rank düsme yok, puani ekle kaydet
                currentUser.setRankPointOfUser(subtractedPoint);
            } else { // puan 100 den asagi düsmemeli o zaten baslangic puani
                currentUser.setRankPointOfUser(100);
            }
            userService.saveUser(currentUser);

        }

        return "Changed rank point. ";
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
                    // log
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String formattedDateTime = now.format(formatter);
                    String message = "User " + user.getUsername() + " leveled up to " + nextLevel.getLevelName() + " on " + formattedDateTime + ".";
                    Log log = new Log(message,"auth");
                    user.addLog(log);//level atladı loglandı
                    userService.saveUser(user);
                    return "Level increased, congratulations!";
                }
            }
            user.setLevelPointOfUser(addedPoint); // seviye atlama yok, puani ekle kaydet
            userService.saveUser(user);
        }

        return "Changed level point.";
    }

}
