package com.hackaton.hackatondonatessystem.dto;

import com.hackaton.hackatondonatessystem.domain.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
public class CauseDTO {

    private Long id;

    private String title;

    private String descrition;

    private Long valueDonated;

    private Long goal;

    private Long minimumDonationPf;

    private Long minimumDonationPj;

    private Sector sector;

    private Member representative;

    private List<DonationCompany> donationCompanies;

    private List<DonationUser> donationUser;

    public CauseDTO(Cause cause) {
        this.id = cause.getId();
        this.title = cause.getTitle();
        this.descrition = cause.getDescrition();
        this.valueDonated = cause.getValueDonated();
        this.goal = cause.getGoal();
        this.minimumDonationPf = cause.getMinimumDonationPf();
        this.minimumDonationPj = cause.getMinimumDonationPj();
        this.sector = cause.getSector();
        this.representative = cause.getRepresentative();
        this.donationCompanies = cause.getDonationCompanies();
        this.donationUser = cause.getDonationUser();
    }
}
