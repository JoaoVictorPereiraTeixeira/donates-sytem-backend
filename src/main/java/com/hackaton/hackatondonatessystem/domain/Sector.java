package com.hackaton.hackatondonatessystem.domain;

import com.hackaton.hackatondonatessystem.dto.SectorDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Long totalDonated;

    @ManyToMany
    private List<Company> companies;

    @OneToMany
    private List<Cause> causes;

    @OneToMany
    private List<CompanyDonation> donationsCompany;

    @OneToMany
    private List<UserDonation> donationsUser;

    public Sector(SectorDTO sectorDTO) {
        this.id = sectorDTO.getId();
        this.name = sectorDTO.getName();
        this.description = sectorDTO.getDescription();
        this.totalDonated = sectorDTO.getTotalDonated();
    }

}
