package com.enviro.assessment.grad001.cynthiadladla.wastesorting.service;

import com.enviro.assessment.grad001.cynthiadladla.wastesorting.dto.WasteCategoryDTO;
import com.enviro.assessment.grad001.cynthiadladla.wastesorting.exceptions.DuplicationOfValues;
import com.enviro.assessment.grad001.cynthiadladla.wastesorting.exceptions.HandleItemNotFound;
import com.enviro.assessment.grad001.cynthiadladla.wastesorting.models.WasteCategory;
import com.enviro.assessment.grad001.cynthiadladla.wastesorting.repository.WasteCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final WasteCategoryRepository wasteCategoryRepository;
    @Autowired
    public CategoryService(WasteCategoryRepository wasteCategoryRepository) {
        this.wasteCategoryRepository = wasteCategoryRepository;
    }

    public WasteCategory saveCategory(WasteCategoryDTO wasteCategoryDTO){

        if(wasteCategoryRepository.existsWasteCategoryByCategory(wasteCategoryDTO.getCategory())) {
            throw new DuplicationOfValues("Category name already exists");
        }

        WasteCategory wasteCategory = WasteCategory.builder()
                .category(wasteCategoryDTO.getCategory())
                .build();
        return wasteCategoryRepository.save(wasteCategory);
    }

    public WasteCategory updateWaste(int id, WasteCategoryDTO wasteCategoryDTO) {

        WasteCategory wasteCategory = wasteCategoryRepository.findById(id)
                .orElseThrow(() -> new HandleItemNotFound("Category not found with id [%s]".formatted(id)));

        wasteCategory.setCategory(wasteCategoryDTO.getCategory());

        try {

            return wasteCategoryRepository.save(wasteCategory);
        } catch (Exception e) {

            throw new RuntimeException("Failed to update category with id " + id, e);
        }
    }


    public List<WasteCategory> getAllCategories(){

        return wasteCategoryRepository.findAll();
    }

    public WasteCategory findCategoryById(int id){

        Optional<WasteCategory> category = wasteCategoryRepository.findById(id);
        return category.orElse(null);

    }
    public WasteCategory findByCategoryOrDefault(String category, WasteCategory defaultCategory) {
        Optional<WasteCategory> optionalCategory = wasteCategoryRepository.findByCategory(category);
        return optionalCategory.orElse(defaultCategory);
    }
    public void deleteCategory(int id) {

        WasteCategory wasteCategory = wasteCategoryRepository.findById(id)
                .orElseThrow(() -> new HandleItemNotFound("Category not found with id [%s]".formatted(id)));

        wasteCategoryRepository.deleteById(id);
    }

}
