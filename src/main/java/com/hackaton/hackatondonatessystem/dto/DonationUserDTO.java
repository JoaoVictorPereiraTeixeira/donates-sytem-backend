package com.hackaton.hackatondonatessystem.dto;

import com.hackaton.hackatondonatessystem.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonationUserDTO extends Donation {

    private Long id;

    private Member donor;

    private String title;

    private String description;

    private String donationDate;

    private Long value;

    private Sector sector;

    private Cause cause;

    public DonationUserDTO(DonationUser donation) {
        this.id = donation.getId();
        this.donor = donation.getDonor();
        this.title = donation.getTitle();
        this.description = donation.getDescription();
        this.donationDate = donation.getDonationDate();
        this.value = donation.getValue();
        this.sector = donation.getSector();
        this.cause = donation.getCause();
    }
}
