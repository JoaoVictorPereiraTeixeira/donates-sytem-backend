package com.hackaton.hackatondonatessystem.domain;

import com.hackaton.hackatondonatessystem.dto.DonationUserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class DonationUser extends Donation{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Member donor;

    public DonationUser(DonationUserDTO donationUserDTO) {
        this.id = donationUserDTO.getId();
        this.donor = donationUserDTO.getDonor();
    }

}
