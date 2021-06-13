package com.hackaton.hackatondonatessystem.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


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

    @OneToOne(cascade=CascadeType.ALL)
    private Permissao permissoes;



}
