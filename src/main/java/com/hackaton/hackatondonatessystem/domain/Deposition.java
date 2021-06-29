package com.hackaton.hackatondonatessystem.domain;


import com.hackaton.hackatondonatessystem.dto.DepositionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Deposition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    User author;

    @Column
    String text;

    @Column
    String date;

    public Deposition(DepositionDTO depositionDTO) {
        this.id = depositionDTO.getId();
        this.author = new User(depositionDTO.getAuthor());
        this.text = depositionDTO.getText();
    }
}
