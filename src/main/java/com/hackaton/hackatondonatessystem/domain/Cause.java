package com.hackaton.hackatondonatessystem.domain;

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
    private String descrition;

    @Column
    private Long valueDonated;

    @Column
    private Long goal;

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


}
