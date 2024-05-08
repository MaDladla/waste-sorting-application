package com.enviro.assessment.grad001.cynthiadladla.wastesorting.repository;

import com.enviro.assessment.grad001.cynthiadladla.wastesorting.models.RecyclingTips;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecyclingTipsRepository extends JpaRepository<RecyclingTips, Integer> {
}
