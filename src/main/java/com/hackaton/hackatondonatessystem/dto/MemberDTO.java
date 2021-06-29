package com.hackaton.hackatondonatessystem.dto;

import com.hackaton.hackatondonatessystem.domain.Deposition;
import com.hackaton.hackatondonatessystem.domain.Member;
import com.hackaton.hackatondonatessystem.domain.Permissao;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private Long id;

    @NotEmpty(message="Field name is required")
    private String name;

    @NotEmpty(message="Field email is required")
    private String email;

    @NotEmpty(message="Field password is required")
    private String password;

    @NotEmpty(message="Field cpf is required")
    private String cpf;

    private List<Deposition> depositions;

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
