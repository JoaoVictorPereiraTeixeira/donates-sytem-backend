package com.hackaton.hackatondonatessystem.domain;

import com.hackaton.hackatondonatessystem.dto.CauseDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private String description;

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
    private List<CompanyDonation> donationCompanies;

    @OneToMany
    private List<UserDonation> userDonation;


    public Cause(CauseDTO cause) {
        this.id = cause.getId();
        this.title = cause.getTitle();
        this.description = cause.getDescription();
        this.valueDonated = cause.getValueDonated();
        this.goal = cause.getGoal();
        this.minimumDonationPf = cause.getMinimumDonationPf();
        this.minimumDonationPj = cause.getMinimumDonationPj();

        if(cause.getSector() != null){
            this.sector = new Sector(cause.getSector());
        }

        if(cause.getRepresentative() != null){
            this.representative = new Member(cause.getRepresentative());
        }

    }

}
