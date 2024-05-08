package com.enviro.assessment.grad001.cynthiadladla.wastesorting.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jmx.export.annotation.ManagedMetric;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="wasteCategory")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class WasteCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String category;

    @OneToMany(mappedBy = "wasteCategory", cascade = CascadeType.ALL)
    private List<DisposalGuide> disposalGuidelines;

    @OneToMany(mappedBy = "wasteCategory", cascade = CascadeType.ALL)
    private List<RecyclingTips> recyclingTips;


}
