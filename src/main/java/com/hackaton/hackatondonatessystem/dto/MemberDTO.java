package com.hackaton.hackatondonatessystem.dto;

import com.hackaton.hackatondonatessystem.domain.DonationUser;
import com.hackaton.hackatondonatessystem.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private Long id;

    private String name;

    private String email;

    private String cpf;

    private String password;

    private List<DonationUser> donations = new ArrayList<>();

    public MemberDTO(Member member) {
        this.name = member.getName();
        this.email = member.getEmail();
        this.donations = member.getDonations();
    }
}
