package com.hackaton.hackatondonatessystem.domain;

import com.hackaton.hackatondonatessystem.dto.CompanyDTO;
import com.hackaton.hackatondonatessystem.dto.SectorDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company extends User {

    @Column
    private String cnpj;

    @OneToMany
    private List<CompanyDonation> donations;

    @ManyToMany
    private Set<Sector> sectors;

    @ManyToMany
    private Set<Cause> causes;

    public Company(CompanyDTO companyDTO) {
        super(companyDTO.getId(),companyDTO.getName(),companyDTO.getEmail(),companyDTO.getPassword(),companyDTO.getConfirmedEmail(),companyDTO.getPermissoes());

        this.cnpj = companyDTO.getCnpj();
        this.donations = companyDTO.getDonations();
        if(sectors != null) {
            this.sectors = companyDTO.getSectors().stream().map(this::convertSectorsToEntity).collect(Collectors.toSet());
        }
    }

    public void addCauses(Cause cause) {
        this.causes.add(cause);
    }

    public void addSectors(Sector sector) {
        this.sectors.add(sector);
    }

    private Sector convertSectorsToEntity(SectorDTO sector){
        return new Sector(sector);
    }
}
