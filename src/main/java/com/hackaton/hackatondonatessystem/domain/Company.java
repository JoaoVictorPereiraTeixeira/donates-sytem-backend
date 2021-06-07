package com.hackaton.hackatondonatessystem.domain;

import com.hackaton.hackatondonatessystem.dto.CompanyDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company extends User {

    @Column
    private String cnpj;

    @OneToMany
    private List<DonationCompany> donations;

    @ManyToMany
    private List<Sector> sectors;

    public Company(CompanyDTO companyDTO) {
        super(companyDTO.getId(),companyDTO.getName(),companyDTO.getEmail(),companyDTO.getPassword());
        this.cnpj = companyDTO.getCnpj();
        this.donations = companyDTO.getDonations();
        this.sectors = companyDTO.getSectors();
    }


}
