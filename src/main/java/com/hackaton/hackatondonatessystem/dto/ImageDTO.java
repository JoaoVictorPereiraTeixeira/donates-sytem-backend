package com.hackaton.hackatondonatessystem.dto;

import com.hackaton.hackatondonatessystem.domain.Cause;
import com.hackaton.hackatondonatessystem.domain.Image;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
public class ImageDTO {

    private Long id;

    private String description;

    private String url;


    public ImageDTO(Image image) {
        this.id = image.getId();
        this.description = image.getDescription();
        this.url = image.getUrl();
    }
}
