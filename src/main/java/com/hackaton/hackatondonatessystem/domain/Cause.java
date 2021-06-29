package com.hackaton.hackatondonatessystem.domain;

import com.hackaton.hackatondonatessystem.dto.CauseDTO;
import com.hackaton.hackatondonatessystem.dto.ImageDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

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
    private String subTitle;

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

    @OneToMany
    private List<Image> images;


    public Cause(CauseDTO cause) {
        this.id = cause.getId();
        this.title = cause.getTitle();
        this.subTitle = cause.getSubTitle();
        this.description = cause.getDescription();
        this.valueDonated = cause.getValueDonated();
        this.goal = cause.getGoal();
        this.minimumDonationPf = cause.getMinimumDonationPf();
        this.minimumDonationPj = cause.getMinimumDonationPj();

        if(cause.getImagesDTO() != null){
            this.images  = cause.getImagesDTO().stream().map(this::convertDTOtoEntity).collect(Collectors.toList());
        }

        if(cause.getSector() != null){
            this.sector = new Sector(cause.getSector());
        }

        if(cause.getRepresentative() != null){
            this.representative = new Member(cause.getRepresentative());
        }

    }


    private Image convertDTOtoEntity(ImageDTO imageDTO){
        return new Image(imageDTO);
    }

}
