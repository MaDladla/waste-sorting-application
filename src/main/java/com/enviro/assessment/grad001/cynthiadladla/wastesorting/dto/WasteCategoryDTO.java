package com.enviro.assessment.grad001.cynthiadladla.wastesorting.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class WasteCategoryDTO {

    @NotNull(message = "Category can not be null")
    @NotBlank
    private String category;

}
