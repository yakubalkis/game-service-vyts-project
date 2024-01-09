package com.game.server.rest;

import com.game.server.entity.Item;
import com.game.server.entity.Speciality;
import com.game.server.mapper.UserMapper;
import com.game.server.rest.dto.SpecialityDto;
import com.game.server.rest.dto.SpecialityRequest;
import com.game.server.service.ItemService;
import com.game.server.service.SpecialityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/specialities")
@AllArgsConstructor
public class SpecialityController {

    private SpecialityService specialityService;
    private ItemService itemService;
    private UserMapper userMapper;


    @PostMapping("")
    public ResponseEntity<?> uploadSpecialitiesData(@RequestParam("file")MultipartFile file){

        this.specialityService.saveSpecialitiesToDatabase(file);
        return ResponseEntity.ok(Map.of("Message","Specialities data uploaded and saved to database successfully"));
    }


    @PostMapping("/add")
    public ResponseEntity<?> createSpeciality(@RequestBody SpecialityRequest specialityRequest) {
        Speciality speciality = new Speciality(specialityRequest.getSpecialityName(),specialityRequest.getDescription() ,specialityRequest.getSymbol(),specialityRequest.getPowerAmount());

        if(specialityRequest.getItemId() != null) { // bir item'a o speciality'i  bağlayarak  olustur.
            try{
                Item item = itemService.findById(specialityRequest.getItemId());
                List<Item> items = new ArrayList<Item>();
                items.add(item);
                speciality.setItems(items);


            } catch (RuntimeException error) {

                return new ResponseEntity(error.getMessage(), HttpStatus.NOT_FOUND);
            }
        }

        specialityService.save(speciality);
        return ResponseEntity.ok(Map.of("Message","Added Speciality to database successfully"));
    }

    @GetMapping("/{itemId}")
    public List<SpecialityDto> getSpecialitiesByItemId(@PathVariable Long itemId) {

        Item item = itemService.findById(itemId);

        return item.getSpecialities().stream().map(userMapper::toSpecialityDto).collect(Collectors.toList());
    }

    @GetMapping
    public ResponseEntity<List<Speciality>> getSpecialities(){

        return new ResponseEntity<>(specialityService.findAll(), HttpStatus.FOUND);

    }

}
