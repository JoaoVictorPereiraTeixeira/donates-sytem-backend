package com.hackaton.hackatondonatessystem.domain;

import com.hackaton.hackatondonatessystem.dto.MemberDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member extends User {

    @Column
    private String cpf;

    @OneToMany
    private List<UserDonation> donations;

    public Member(MemberDTO memberDTO) {
        super(memberDTO.getId(),memberDTO.getName(),memberDTO.getEmail(),"");
    }

}
