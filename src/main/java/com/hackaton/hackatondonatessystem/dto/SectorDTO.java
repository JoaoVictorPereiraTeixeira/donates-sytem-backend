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

    private String descrition;

    private Long totalDonated;

    private List<CompanyDTO> companies;

    private List<CauseDTO> causes;

    private List<DonationCompany> donationsCompany;

    private List<DonationUser> donationsUser;

    public SectorDTO(Sector sector) {
        this.id = sector.getId();
        this.name = sector.getName();
        this.descrition = sector.getDescrition();
        this.totalDonated = sector.getTotalDonated();
        this.companies = sector.getCompanies().stream().map(this::convertCompanyToDTO).collect(Collectors.toList());
        this.causes = sector.getCauses().stream().map(this::convertCauseToDTO).collect(Collectors.toList());
        this.donationsCompany = sector.getDonationsCompany();
        this.donationsUser = sector.getDonationsUser();
    }

    public CompanyDTO convertCompanyToDTO (Company company) {
        return new CompanyDTO(company);
    }

    public CauseDTO convertCauseToDTO(Cause cause) {
        return new CauseDTO(cause);
    }

}
