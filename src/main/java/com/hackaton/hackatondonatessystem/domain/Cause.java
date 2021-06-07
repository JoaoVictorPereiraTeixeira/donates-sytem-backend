package com.hackaton.hackatondonatessystem.domain;

import com.hackaton.hackatondonatessystem.dto.CauseDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Cause {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String title;

    @Column
    private String descrition;

    @Column
    private Long valueDonated;

    @Column
    private Double goal;

    @Column
    private Long minimumDonationPf;

    @Column
    private Long minimumDonationPj;

    @ManyToOne
    private Sector sector;

    @ManyToOne
    private Member representative;

    @OneToMany
    private List<DonationCompany> donationCompanies;

    @OneToMany
    private List<DonationUser> donationUser;


    public Cause(CauseDTO cause) {
        this.id = cause.getId();
        this.title = cause.getTitle();
        this.descrition = cause.getDescrition();
        this.valueDonated = cause.getValueDonated();
        this.goal = cause.getGoal();
        this.minimumDonationPf = cause.getMinimumDonationPf();
        this.minimumDonationPj = cause.getMinimumDonationPj();
        this.sector = new Sector(cause.getSector());
        this.representative = new Member(cause.getRepresentative());
        this.donationCompanies = cause.getDonationCompanies();
        this.donationUser = cause.getDonationUser();
    }

}
