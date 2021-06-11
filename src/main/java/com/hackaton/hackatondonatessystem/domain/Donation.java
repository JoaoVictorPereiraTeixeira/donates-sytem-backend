package com.hackaton.hackatondonatessystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String donationDate;

    @Column
    private Long value;

    @ManyToOne
    private Sector sector;

    @ManyToOne
    private Cause cause;

}
