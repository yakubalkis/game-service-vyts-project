package com.game.server.service;


import com.game.server.entity.Speciality;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SpecialityService {

    List<Speciality> findAll();

    void save(Speciality speciality);

    Speciality findById(Long id);

    void deleteById(Long id);

    public void saveSpecialitiesToDatabase(MultipartFile file);

}
