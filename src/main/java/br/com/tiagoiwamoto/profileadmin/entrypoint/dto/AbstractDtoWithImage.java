package br.com.tiagoiwamoto.profileadmin.entrypoint.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AbstractDtoWithImage extends AbstractDto {

    private String pathOfImage;
    private String pathOfImageThumb;
}
