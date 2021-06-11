package com.hackaton.hackatondonatessystem.dto;

import com.hackaton.hackatondonatessystem.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {

    private Long id;

    private String name;

    private String email;

    private String password;

    private String cnpj;

    private List<CompanyDonation> donations;

    private List<SectorDTO> sectors;

    public CompanyDTO(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.email = company.getEmail();
        this.cnpj = company.getCnpj();
        this.donations = company.getDonations();
        if(sectors  != null){
            this.sectors = company.getSectors().stream().map(this::convertSectorsToDTO).collect(Collectors.toList());
        }
    }

    private SectorDTO convertSectorsToDTO(Sector sector){
        return new SectorDTO(sector);
    }


}
