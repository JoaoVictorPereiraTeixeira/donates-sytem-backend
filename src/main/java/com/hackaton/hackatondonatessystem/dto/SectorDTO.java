package com.hackaton.hackatondonatessystem.dto;

import com.hackaton.hackatondonatessystem.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectorDTO {

    private Long id;

    private String name;

    private String description;

    private Long totalDonated;

    public SectorDTO(Sector sector) {
        this.id = sector.getId();
        this.name = sector.getName();
        this.description = sector.getDescription();
        this.totalDonated = sector.getTotalDonated();
    }

}
