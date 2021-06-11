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

    private String description;

    private Long valueDonated;

    private Double goal;

    private Long minimumDonationPf;

    private Long minimumDonationPj;

    private SectorDTO sector;

    private MemberDTO representative;


    public CauseDTO(Cause cause) {
        this.id = cause.getId();
        this.title = cause.getTitle();
        this.description = cause.getDescription();
        this.valueDonated = cause.getValueDonated();
        this.goal = cause.getGoal();
        this.minimumDonationPf = cause.getMinimumDonationPf();
        this.minimumDonationPj = cause.getMinimumDonationPj();
        this.sector = new SectorDTO(cause.getSector());
        this.representative = new MemberDTO(cause.getRepresentative());
    }
}
