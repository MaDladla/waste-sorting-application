package com.enviro.assessment.grad001.cynthiadladla.wastesorting.controller;

import com.enviro.assessment.grad001.cynthiadladla.wastesorting.dto.DisposalGuideDTO;
import com.enviro.assessment.grad001.cynthiadladla.wastesorting.models.DisposalGuide;
import com.enviro.assessment.grad001.cynthiadladla.wastesorting.service.GuideService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guide")
public class DisposalGuidController {

    public final GuideService guideService;

    @Autowired
    public DisposalGuidController(GuideService guideService) {
        this.guideService = guideService;
    }


    @PostMapping("/postGuide")
    public ResponseEntity<DisposalGuideDTO> addGuide(@RequestBody @Valid DisposalGuideDTO disposalGuideDTO){

     return new ResponseEntity<>(guideService.saveDisposalGuide(disposalGuideDTO), HttpStatus.CREATED);
    }

    @PostMapping("/updateDisposal/{id}")
    public ResponseEntity<DisposalGuideDTO> updateCategory(@PathVariable int id, @Valid @RequestBody DisposalGuideDTO disposalGuideDTO) {
        DisposalGuideDTO updatedGuide = guideService.updateDisposalGuide(id, disposalGuideDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedGuide);
    }

    @GetMapping("/getAllGuides")
    public List<DisposalGuide> getAllGuides(){
        return guideService.getAllDisposalGuid();
    }

    @GetMapping("/getGuide/{id}")
    public DisposalGuide getGuideById(@PathVariable int id){
        return guideService.getDisposalGuide(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable int id) {
        guideService.deleteDisposalGuide(id);
        return ResponseEntity.status(HttpStatus.OK).body("Disposal guide successfully deleted");
    }
}
