package com.hackaton.hackatondonatessystem.dto;


import com.hackaton.hackatondonatessystem.domain.Deposition;
import com.hackaton.hackatondonatessystem.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepositionDTO {

    private Long id;

    private UserDTO author;

    String text;

    String date;

    public DepositionDTO(Deposition deposition) {
        this.id = deposition.getId();
        this.author = new UserDTO(deposition.getAuthor());
        this.text = deposition.getText();
        this.date = deposition.getDate();
    }
}
