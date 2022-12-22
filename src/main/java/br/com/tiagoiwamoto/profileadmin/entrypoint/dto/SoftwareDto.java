package br.com.tiagoiwamoto.profileadmin.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SoftwareDto extends AbstractDtoWithImage{

    private String name;
    private String description;
    private String url;
    private String urlMirror;

}
