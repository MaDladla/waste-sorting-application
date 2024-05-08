package com.enviro.assessment.grad001.cynthiadladla.wastesorting.controller;

import com.enviro.assessment.grad001.cynthiadladla.wastesorting.dto.DisposalGuideDTO;
import com.enviro.assessment.grad001.cynthiadladla.wastesorting.dto.RecyclingTipsDTO;
import com.enviro.assessment.grad001.cynthiadladla.wastesorting.dto.WasteCategoryDTO;
import com.enviro.assessment.grad001.cynthiadladla.wastesorting.models.DisposalGuide;
import com.enviro.assessment.grad001.cynthiadladla.wastesorting.models.RecyclingTips;
import com.enviro.assessment.grad001.cynthiadladla.wastesorting.models.WasteCategory;
import com.enviro.assessment.grad001.cynthiadladla.wastesorting.service.RecyclingTipService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tips")
public class RecyclingTipController {

    public final RecyclingTipService recyclingTipService;

    @Autowired
    public RecyclingTipController(RecyclingTipService recyclingTipService) {
        this.recyclingTipService = recyclingTipService;
    }

    @PostMapping("/addRecyclingTip")
    public ResponseEntity<RecyclingTipsDTO> saveTips(@RequestBody @Valid RecyclingTipsDTO recyclingTipsDTO){

        return new ResponseEntity<>(recyclingTipService.saveRecyclingTip(recyclingTipsDTO), HttpStatus.CREATED);
    }

    @PostMapping("/updateTip/{id}")
    public ResponseEntity<RecyclingTipsDTO> updateCategory(@PathVariable int id, @Valid @RequestBody RecyclingTipsDTO recyclingTipsDTO) {
        RecyclingTipsDTO recyclingTips= recyclingTipService.updateRecyclingTips(id, recyclingTipsDTO);
        return ResponseEntity.status(HttpStatus.OK).body(recyclingTips);
    }
    @GetMapping("/getAllTips")
    public List<RecyclingTips> getAllTips(){
        return recyclingTipService.getAllRecyclingTips();
    }

    @GetMapping("/getTip/{id}")
    public RecyclingTips getTipById(@PathVariable int id){
        return recyclingTipService.getTipsById(id);
    }

    @DeleteMapping("/deleteTip/{id}")
    public ResponseEntity<String> deleteTip(@PathVariable int id) {
        recyclingTipService.deleteRecyclingTip(id);
        return ResponseEntity.status(HttpStatus.OK).body("Tip successfully deleted");
    }
}
