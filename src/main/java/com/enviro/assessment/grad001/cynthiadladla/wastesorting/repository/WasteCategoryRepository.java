package com.enviro.assessment.grad001.cynthiadladla.wastesorting.repository;

import com.enviro.assessment.grad001.cynthiadladla.wastesorting.models.WasteCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WasteCategoryRepository extends JpaRepository<WasteCategory, Integer> {

    boolean existsWasteCategoryByCategory(String category);
    Optional<WasteCategory> findByCategory(String category);
}
