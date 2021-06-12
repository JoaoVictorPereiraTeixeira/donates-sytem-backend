package com.hackaton.hackatondonatessystem.domain;

import com.hackaton.hackatondonatessystem.dto.CompanyDonationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class CompanyDonation extends Donation{

    @ManyToOne
    private Company donor;

    public CompanyDonation(CompanyDonationDTO companyDonationDTO) {
        super(
                companyDonationDTO.getId(),
                companyDonationDTO.getTitle(),
                companyDonationDTO.getDescription(),
                companyDonationDTO.getDonationDate(),
                companyDonationDTO.getValue(),
                new Sector(companyDonationDTO.getSector()),
                new Cause(companyDonationDTO.getCause())
        );

        this.donor = new Company(companyDonationDTO.getDonor());
    }

}
