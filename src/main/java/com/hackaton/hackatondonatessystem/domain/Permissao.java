package com.hackaton.hackatondonatessystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Permissao")
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="admin")
    private boolean admin;

    @Column(name = "comum_user")
    private boolean comumUser;

    @Column(name="organizador")
    private boolean organizer;

    @Column(name="web_application")
    private boolean webApplication;


}