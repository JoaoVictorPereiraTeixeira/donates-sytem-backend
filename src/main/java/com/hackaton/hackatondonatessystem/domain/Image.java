package com.hackaton.hackatondonatessystem.domain;

import com.hackaton.hackatondonatessystem.dto.ImageDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String description;

    @Column
    private String url;

    public Image(ImageDTO imageDTO) {
        this.id = imageDTO.getId();
        this.description = imageDTO.getDescription();
        this.url = imageDTO.getUrl();
    }
}
