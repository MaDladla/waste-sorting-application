package com.enviro.assessment.grad001.cynthiadladla.wastesorting.repository;

import com.enviro.assessment.grad001.cynthiadladla.wastesorting.models.DisposalGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisposalGuideRepository extends JpaRepository<DisposalGuide, Integer> {
}
