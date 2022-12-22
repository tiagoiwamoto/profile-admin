package br.com.tiagoiwamoto.profileadmin.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResumeDto extends AbstractDto{

    private String title;
    private String description;
    private String language;
    private String type;
    private String url;
    private String embed;

}