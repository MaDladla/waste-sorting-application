package com.enviro.assessment.grad001.cynthiadladla.wastesorting.controller;

import com.enviro.assessment.grad001.cynthiadladla.wastesorting.dto.WasteCategoryDTO;
import com.enviro.assessment.grad001.cynthiadladla.wastesorting.models.WasteCategory;
import com.enviro.assessment.grad001.cynthiadladla.wastesorting.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class WasteCategoryController {

    public final CategoryService categoryService;
    @Autowired
    public WasteCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/addCategory")
    public ResponseEntity<WasteCategory> saveCategory(@RequestBody @Valid WasteCategoryDTO wasteCategoryDTO){

        return new ResponseEntity<>(categoryService.saveCategory(wasteCategoryDTO), HttpStatus.CREATED);

    }

    @PostMapping("/updateCategory/{id}")
    public ResponseEntity<WasteCategory> updateCategory(@PathVariable int id, @Valid @RequestBody WasteCategoryDTO wasteCategoryDTO) {
        WasteCategory updatedCategory = categoryService.updateWaste(id, wasteCategoryDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCategory);
    }

    @GetMapping("/getAllCategories")
    public List<WasteCategory> getAllCategories(){
        return categoryService.getAllCategories();
    }
    @GetMapping("/getCategory/{id}")
    public WasteCategory getCategory(@PathVariable int id){

        return categoryService.findCategoryById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body("Category successfully deleted");
    }
}
