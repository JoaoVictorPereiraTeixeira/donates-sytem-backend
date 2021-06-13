package com.hackaton.hackatondonatessystem.dto;

import com.hackaton.hackatondonatessystem.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDonationDTO {

    private Long id;

    @NotEmpty(message="Field name is required")
    private CompanyDTO donor;

    @NotEmpty(message="Field title is required")
    private String title;

    @NotEmpty(message="Field description is required")
    private String description;

    @NotEmpty(message="Field donationDate is required")
    private String donationDate;

    @NotEmpty(message="Field value is required")
    private Long value;

    @NotEmpty(message="Field sector is required")
    private SectorDTO sector;

    @NotEmpty(message="Field cause is required")
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
