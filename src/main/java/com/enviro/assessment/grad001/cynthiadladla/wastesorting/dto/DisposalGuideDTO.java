package com.enviro.assessment.grad001.cynthiadladla.wastesorting.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class DisposalGuideDTO {

    @NotNull(message = "guideline cannot be null")
    @NotBlank(message = "guideline cannot be blank")
    private String guideline;

    @NotNull(message = "wasteCategoryId cannot be null")
    private Integer wasteCategoryId;
}
