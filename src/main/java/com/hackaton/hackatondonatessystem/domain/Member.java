package com.hackaton.hackatondonatessystem.domain;

import com.hackaton.hackatondonatessystem.dto.DonationUserDTO;
import com.hackaton.hackatondonatessystem.dto.MemberDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member extends User {

    @Column
    private String cpf;

    @OneToMany
    private List<DonationUser> donations;

    public Member(MemberDTO memberDTO) {
        super(memberDTO.getId(),memberDTO.getName(),memberDTO.getEmail(),memberDTO.getPassword());

        this.cpf = memberDTO.getCpf();
        this.donations = memberDTO.getDonations();
    }

}
