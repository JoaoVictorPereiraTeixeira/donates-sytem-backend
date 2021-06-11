package com.hackaton.hackatondonatessystem.domain;

import com.hackaton.hackatondonatessystem.dto.DonationUserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class UserDonation extends Donation{

    @ManyToOne
    private Member donor;

    public UserDonation(DonationUserDTO donationUserDTO) {
        this.donor = new Member(donationUserDTO.getDonor());
    }

}
