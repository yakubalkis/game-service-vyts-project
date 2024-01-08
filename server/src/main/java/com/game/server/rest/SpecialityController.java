package com.game.server.rest;

import com.game.server.entity.Speciality;
import com.game.server.service.SpecialityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/specialities")
@AllArgsConstructor
public class SpecialityController {

    private SpecialityService specialityService;
    @PostMapping("")
    public ResponseEntity<?> uploadSpecialitiesData(@RequestParam("file")MultipartFile file){

        this.specialityService.saveSpecialitiesToDatabase(file);
        return ResponseEntity.ok(Map.of("Message","Specialities data uploaded and saved to database successfully"));
    }


    @GetMapping
    public ResponseEntity<List<Speciality>> getSpecialities(){

        return new ResponseEntity<>(specialityService.findAll(), HttpStatus.FOUND);

    }

}
