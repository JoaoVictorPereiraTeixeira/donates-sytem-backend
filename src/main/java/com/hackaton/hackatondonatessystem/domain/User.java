package com.hackaton.hackatondonatessystem.domain;

import com.hackaton.hackatondonatessystem.dto.UserDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private Boolean confirmedEmail;

    @OneToMany
    private List<Deposition> depositions;

    @OneToOne(cascade=CascadeType.ALL)
    private Permissao permissoes;

    public User(UserDTO author) {
        this.id = author.getId();
    }
}
