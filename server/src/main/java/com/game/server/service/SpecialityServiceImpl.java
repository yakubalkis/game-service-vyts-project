package com.game.server.service;

import com.game.server.entity.Speciality;
import com.game.server.repository.SpecialityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class SpecialityServiceImpl implements SpecialityService {

    private final SpecialityRepository specialityRepository;
    private final ExcelImportService excelImportService;
    @Override
    public List<Speciality> findAll() {
        return specialityRepository.findAll();
    }

    @Override
    public void save(Speciality speciality) {
        specialityRepository.save(speciality);
    }

    @Override
    public Speciality findById(Long id) {
        Optional<Speciality> result = specialityRepository.findById(id);
        Speciality speciality = null;

        if(result.isPresent()) {
            speciality = result.get();
        } else {
            throw new RuntimeException("Did not found speciality with id: "+ id);
        }

        return speciality;
    }

    public void saveSpecialitiesToDatabase(MultipartFile file){

        if(excelImportService.isValidExcelFile(file)){
            try {
                List<Speciality> specialities = excelImportService.getSpecialitiesDataFromExcel(file.getInputStream());
                this.specialityRepository.saveAll(specialities);

            } catch (IOException e) {

                throw new IllegalArgumentException("The file is not a valid excel file");

            }
        }

    }

    @Override
    public void deleteById(Long id) {
        specialityRepository.deleteById(id);
    }
}
