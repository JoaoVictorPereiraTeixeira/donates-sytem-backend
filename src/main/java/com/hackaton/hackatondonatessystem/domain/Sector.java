package com.hackaton.hackatondonatessystem.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Sector {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String descrition;

    @Column
    private Long totalDonated;

    @ManyToMany
    private List<Company> companies;

    @OneToMany
    private List<Cause> causes;

    @OneToMany
    private List<DonationCompany> donationsCompany;

    @OneToMany
    private List<DonationUser> donationsUser;

}
