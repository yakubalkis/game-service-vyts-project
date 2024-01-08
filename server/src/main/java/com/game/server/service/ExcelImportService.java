package com.game.server.service;
import com.game.server.entity.Category;
import com.game.server.entity.Item;
import com.game.server.entity.PriceDate;
import com.game.server.entity.Speciality;
import com.game.server.repository.CategoryRepository;
import com.game.server.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
@RequiredArgsConstructor
@Service
public class ExcelImportService {

    public final CategoryRepository categoryRepository;
    public final ItemRepository itemRepository;
    public boolean isValidExcelFile(MultipartFile file){
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" );
    }

    public List<Item> getItemsDataFromExcel(InputStream inputStream){

        List<Item> items = new ArrayList<>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("items");

            int rowIndex = 0;

            for(Row row : sheet) {
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                Item item = new Item();
                while (cellIterator.hasNext()) {


                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0 -> {long longValue = (long) cell.getNumericCellValue();
                            item.setId(longValue);
                        }
                        case 1 -> item.setItemName(cell.getStringCellValue());
                        case 2 -> item.setSymbol(cell.getStringCellValue());
                        case 3 -> {
                            long longValue = (long) cell.getNumericCellValue();
                            item.setCategory(categoryRepository.getById(longValue));
                        }
                        default -> {
                        }

                    }
                    cellIndex++;
                }
                items.add(item);
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        return items;
    }

    public List<Category> getCategoriesDataFromExcel(InputStream inputStream){

        List<Category> categories = new ArrayList<>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("categories");

            int rowIndex = 0;

            for(Row row : sheet) {
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                Category category = new Category();
                while (cellIterator.hasNext()) {


                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0 -> {long longValue = (long) cell.getNumericCellValue();
                            category.setId(longValue);
                        }
                        case 1 -> category.setCategoryName(cell.getStringCellValue());
                        case 2 -> category.setSymbol(cell.getStringCellValue());
                        default -> {
                        }

                    }
                    cellIndex++;
                }
                categories.add(category);
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        return categories;
    }

    public List<Speciality> getSpecialitiesDataFromExcel(InputStream inputStream){

        List<Speciality> specialities = new ArrayList<>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("specialities");

            int rowIndex = 0;

            for(Row row : sheet) {
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                Speciality speciality = new Speciality();
                while (cellIterator.hasNext()) {


                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0 -> {long longValue = (long) cell.getNumericCellValue();
                            speciality.setId(longValue);
                        }
                        case 1 -> speciality.setSpecialityName(cell.getStringCellValue());
                        case 2 -> speciality.setDescription(cell.getStringCellValue());
                        case 3 -> speciality.setSymbol(cell.getStringCellValue());
                        case 4 -> speciality.setPowerAmount((int)cell.getNumericCellValue());
                        default -> {
                        }

                    }
                    cellIndex++;
                }
                specialities.add(speciality);
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        return specialities;
    }


    public List<PriceDate> getPriceDatesDataFromExcel(InputStream inputStream){

        List<PriceDate> priceDates = new ArrayList<>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("priceDates");

            int rowIndex = 0;

            for(Row row : sheet) {
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                PriceDate priceDate = new PriceDate();
                while (cellIterator.hasNext()) {


                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0 -> {long longValue = (long) cell.getNumericCellValue();
                            priceDate.setId(longValue);
                        }
                        case 1 -> priceDate.setPrice((int) cell.getNumericCellValue());
                        case 2 -> priceDate.setPriceDate(cell.getStringCellValue());
                        case 3 -> priceDate.setPriceType(cell.getStringCellValue());
                        case 4 -> {
                            long longValue = (long) cell.getNumericCellValue();
                            priceDate.setItem(itemRepository.getById(longValue));
                        }
                        default -> {
                        }

                    }
                    cellIndex++;
                }
                priceDates.add(priceDate);
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        return priceDates;
    }

}