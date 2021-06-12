package com.hackaton.hackatondonatessystem.domain;

import com.hackaton.hackatondonatessystem.dto.MemberDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


import javax.persistence.*;
import java.util.List;



@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class Member extends User {

    @Column
    private String cpf;

    @OneToMany
    private List<UserDonation> donations;

    public Member(MemberDTO memberDTO) {
        super(memberDTO.getId(),memberDTO.getName(),memberDTO.getEmail(),memberDTO.getPassword(),false, memberDTO.getPermissoes());
    }

}
