package com.enviro.assessment.grad001.cynthiadladla.wastesorting.service;

import com.enviro.assessment.grad001.cynthiadladla.wastesorting.dto.DisposalGuideDTO;
import com.enviro.assessment.grad001.cynthiadladla.wastesorting.exceptions.HandleItemNotFound;
import com.enviro.assessment.grad001.cynthiadladla.wastesorting.models.DisposalGuide;
import com.enviro.assessment.grad001.cynthiadladla.wastesorting.models.WasteCategory;
import com.enviro.assessment.grad001.cynthiadladla.wastesorting.repository.DisposalGuideRepository;
import com.enviro.assessment.grad001.cynthiadladla.wastesorting.repository.WasteCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuideService {

    private final DisposalGuideRepository disposalGuideRepository;
    private final WasteCategoryRepository wasteCategoryRepository;

    @Autowired
    public GuideService(DisposalGuideRepository disposalGuideRepository,  WasteCategoryRepository wasteCategoryRepository ) {
        this.disposalGuideRepository = disposalGuideRepository;
        this. wasteCategoryRepository =  wasteCategoryRepository;
    }

    public DisposalGuideDTO saveDisposalGuide(DisposalGuideDTO disposalGuideDTO){

        WasteCategory findCategory = wasteCategoryRepository
                .findById(disposalGuideDTO.getWasteCategoryId())
                .orElseThrow( () -> new HandleItemNotFound("Category not found with id [%s]".formatted(disposalGuideDTO.getWasteCategoryId())));

            WasteCategory wasteCategory = new WasteCategory();
            wasteCategory.setId(disposalGuideDTO.getWasteCategoryId());

            DisposalGuide disposalGuide = new DisposalGuide();
            disposalGuide.setGuideline(disposalGuideDTO.getGuideline());
            disposalGuide.setWasteCategory(wasteCategory);
            disposalGuideRepository.save(disposalGuide);

        return disposalGuideDTO;
    }

    public DisposalGuideDTO updateDisposalGuide(int id, DisposalGuideDTO disposalGuideDTO) {

        DisposalGuide disposalGuide = disposalGuideRepository.findById(id)
                .orElseThrow(() -> new HandleItemNotFound("Guide not found with id [%s]".formatted(id)));

        disposalGuide.setGuideline(disposalGuideDTO.getGuideline());

        disposalGuide = disposalGuideRepository.save(disposalGuide);


        return mapDisposalGuideToDTO(disposalGuide);
    }

    private DisposalGuideDTO mapDisposalGuideToDTO(DisposalGuide disposalGuide) {
        DisposalGuideDTO dto = new DisposalGuideDTO();
        dto.setGuideline(disposalGuide.getGuideline());
        dto.setWasteCategoryId(disposalGuide.getWasteCategory().getId());
        return dto;
    }

    public DisposalGuide getDisposalGuide(int id){
        DisposalGuide disposalGuide = disposalGuideRepository.findById(id)
                .orElseThrow(() -> new HandleItemNotFound("Guide not found with id [%s]".formatted(id)));

        return disposalGuide;
    }

    public List<DisposalGuide> getAllDisposalGuid() {
        return disposalGuideRepository.findAll();
    }

    public void deleteDisposalGuide(int id) {

        DisposalGuide disposalGuide = disposalGuideRepository.findById(id)
                .orElseThrow(() -> new HandleItemNotFound("Disposal not found with id [%s]".formatted(id)));

        disposalGuideRepository.deleteById(id);
    }
}
