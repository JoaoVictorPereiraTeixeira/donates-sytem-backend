package com.hackaton.hackatondonatessystem.dto;

import com.hackaton.hackatondonatessystem.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDonationDTO {

    private Long id;

    private CompanyDTO donor;

    private String title;

    private String description;

    private String donationDate;

    private Long value;

    private SectorDTO sector;

    private CauseDTO cause;

    public CompanyDonationDTO(CompanyDonation donation) {
        this.id = donation.getId();
        this.title = donation.getTitle();
        this.description = donation.getDescription();
        this.donationDate = donation.getDonationDate();
        this.value = donation.getValue();

        this.donor =  new CompanyDTO(donation.getDonor());
        this.sector = new SectorDTO(donation.getSector());
        this.cause = new CauseDTO(donation.getCause());
    }

}
