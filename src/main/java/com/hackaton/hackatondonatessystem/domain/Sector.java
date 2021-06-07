package com.hackaton.hackatondonatessystem.domain;

import com.hackaton.hackatondonatessystem.dto.CauseDTO;
import com.hackaton.hackatondonatessystem.dto.CompanyDTO;
import com.hackaton.hackatondonatessystem.dto.SectorDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

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
    private String descrition;

    @Column
    private Long totalDonated;

    @ManyToMany
    private List<Company> companies;

    @OneToMany
    private List<Cause> causes;

    @OneToMany
    private List<DonationCompany> donationsCompany;

    @OneToMany
    private List<DonationUser> donationsUser;

    public Sector(SectorDTO sectorDTO) {
        this.id = sectorDTO.getId();
        this.name = sectorDTO.getName();
        this.descrition = sectorDTO.getDescrition();
        this.totalDonated = sectorDTO.getTotalDonated();
        this.companies = sectorDTO.getCompanies().stream().map(this::convertCompanyToEntity).collect(Collectors.toList());
        this.causes = sectorDTO.getCauses().stream().map(this::convertCauseToEntity).collect(Collectors.toList());
        this.donationsCompany = sectorDTO.getDonationsCompany();
        this.donationsUser = sectorDTO.getDonationsUser();
    }

    public Company convertCompanyToEntity (CompanyDTO companyDTO) {
        return new Company(companyDTO);
    }

    public Cause convertCauseToEntity(CauseDTO causeDTO) {
        return new Cause(causeDTO);
    }
}
