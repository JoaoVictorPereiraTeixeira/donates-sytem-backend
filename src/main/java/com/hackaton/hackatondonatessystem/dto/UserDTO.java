package com.hackaton.hackatondonatessystem.dto;

import com.hackaton.hackatondonatessystem.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    Long id;
    String name;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
    }
}
