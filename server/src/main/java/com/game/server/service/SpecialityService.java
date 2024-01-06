package com.game.server.service;


import com.game.server.entity.Speciality;

import java.util.List;

public interface SpecialityService {

    List<Speciality> findAll();

    void save(Speciality speciality);

    Speciality findById(Long id);

    void deleteById(Long id);
}
