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
public class CompanyDTO {

    private Long id;

    private String name;

    private String email;

    private String password;

    private String cnpj;

    private List<DonationCompany> donations;

    private List<Sector> sectors;

    public CompanyDTO(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.email = company.getEmail();
        this.cnpj = company.getCnpj();
        this.donations = company.getDonations();
        this.sectors = company.getSectors();
    }
}
