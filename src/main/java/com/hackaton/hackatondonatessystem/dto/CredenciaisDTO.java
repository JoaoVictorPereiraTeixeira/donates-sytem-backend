package com.hackaton.hackatondonatessystem.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class CredenciaisDTO {

    @NotEmpty(message="Field login is required")
    private String login;

    @NotEmpty(message="Field password is required")
    private String password;

}