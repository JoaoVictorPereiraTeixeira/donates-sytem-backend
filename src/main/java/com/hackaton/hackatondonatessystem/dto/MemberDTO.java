package com.hackaton.hackatondonatessystem.dto;

import com.hackaton.hackatondonatessystem.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private Long id;

    private String name;

    private String email;


    public MemberDTO(Member member) {
        this.name = member.getName();
        this.email = member.getEmail();
    }
}
