package com.hackaton.hackatondonatessystem.dto;

import com.hackaton.hackatondonatessystem.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {

    private Long id;

    @NotEmpty(message="Field name is required")
    private String name;

    @NotEmpty(message="Field email is required")
    private String email;

    @NotEmpty(message="Field password is required")
    private String password;

    @NotEmpty(message="Field cnpj is required")
    private String cnpj;

    private List<CompanyDonation> donations;

    private List<Deposition> depositions;

    private List<SectorDTO> sectors;

    private Permissao permissoes;

    private Boolean confirmedEmail;

    public CompanyDTO(Company company) {
        this.name = company.getName();
        this.email = company.getEmail();
        this.cnpj = company.getCnpj();
        this.confirmedEmail = company.getConfirmedEmail();
        if(sectors  != null){
            this.sectors = company.getSectors().stream().map(this::convertSectorsToDTO).collect(Collectors.toList());
        }
    }
    private SectorDTO convertSectorsToDTO(Sector sector){
        return new SectorDTO(sector);
    }

}
