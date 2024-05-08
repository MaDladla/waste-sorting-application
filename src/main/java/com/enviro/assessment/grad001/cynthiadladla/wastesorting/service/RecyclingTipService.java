package com.enviro.assessment.grad001.cynthiadladla.wastesorting.service;

import com.enviro.assessment.grad001.cynthiadladla.wastesorting.dto.DisposalGuideDTO;
import com.enviro.assessment.grad001.cynthiadladla.wastesorting.dto.RecyclingTipsDTO;
import com.enviro.assessment.grad001.cynthiadladla.wastesorting.exceptions.HandleItemNotFound;
import com.enviro.assessment.grad001.cynthiadladla.wastesorting.models.DisposalGuide;
import com.enviro.assessment.grad001.cynthiadladla.wastesorting.models.RecyclingTips;
import com.enviro.assessment.grad001.cynthiadladla.wastesorting.models.WasteCategory;
import com.enviro.assessment.grad001.cynthiadladla.wastesorting.repository.RecyclingTipsRepository;
import com.enviro.assessment.grad001.cynthiadladla.wastesorting.repository.WasteCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecyclingTipService {

    public final RecyclingTipsRepository recyclingTipsRepository;
    private final WasteCategoryRepository wasteCategoryRepository;

    @Autowired
    public RecyclingTipService(RecyclingTipsRepository recyclingTipsRepository, WasteCategoryRepository wasteCategoryRepository) {
        this.recyclingTipsRepository = recyclingTipsRepository;
        this. wasteCategoryRepository =  wasteCategoryRepository;
    }

    public RecyclingTipsDTO saveRecyclingTip(RecyclingTipsDTO recyclingTipsDTO){

        WasteCategory findCategory = wasteCategoryRepository
                .findById(recyclingTipsDTO.getWasteCategoryId())
                .orElseThrow( () -> new HandleItemNotFound("Category not found with id [%s]".formatted(recyclingTipsDTO.getWasteCategoryId())));

        WasteCategory wasteCategory = new WasteCategory();
        wasteCategory.setId(recyclingTipsDTO.getWasteCategoryId());

        RecyclingTips recyclingTips = new RecyclingTips();
        recyclingTips.setTip(recyclingTipsDTO.getTip());
        recyclingTips.setWasteCategory(wasteCategory);
        recyclingTipsRepository.save(recyclingTips);

        return recyclingTipsDTO;
    }

    public RecyclingTipsDTO updateRecyclingTips(int id, RecyclingTipsDTO recyclingTipsDTO) {

        RecyclingTips recyclingTips = recyclingTipsRepository.findById(id)
                .orElseThrow(() -> new HandleItemNotFound("Tip not found with id [%s]".formatted(id)));

        recyclingTips.setTip(recyclingTipsDTO.getTip());
        recyclingTips = recyclingTipsRepository.save(recyclingTips);

        return mapRecyclingTipsToDTO(recyclingTips);
    }


    private RecyclingTipsDTO mapRecyclingTipsToDTO(RecyclingTips recyclingTips) {
        RecyclingTipsDTO dto = new RecyclingTipsDTO();
        dto.setTip(recyclingTips.getTip());
        dto.setWasteCategoryId(recyclingTips.getWasteCategory().getId());
        return dto;
    }

    public List<RecyclingTips> getAllRecyclingTips() {

        return recyclingTipsRepository.findAll();
    }

    public RecyclingTips getTipsById(int id){
        RecyclingTips  recyclingTips = recyclingTipsRepository.findById(id)
                .orElseThrow(() -> new HandleItemNotFound("Recycling tip not found with id [%s]".formatted(id)));

        return recyclingTips;
    }
    public void deleteRecyclingTip(int id) {

        RecyclingTips recyclingTips =   recyclingTipsRepository.findById(id)
                .orElseThrow(() -> new HandleItemNotFound("Tip not found with id [%s]".formatted(id)));

        recyclingTipsRepository.deleteById(id);
    }
}
