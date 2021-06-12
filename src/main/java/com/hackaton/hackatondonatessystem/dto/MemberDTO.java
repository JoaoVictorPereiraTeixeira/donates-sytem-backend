package com.hackaton.hackatondonatessystem.dto;

import com.hackaton.hackatondonatessystem.domain.Member;
import com.hackaton.hackatondonatessystem.domain.Permissao;
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

    private String password;

    private Permissao permissoes;

    private Boolean confirmedEmail;

    public MemberDTO(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.permissoes = member.getPermissoes();
        this.confirmedEmail = member.getConfirmedEmail();
    }
}
