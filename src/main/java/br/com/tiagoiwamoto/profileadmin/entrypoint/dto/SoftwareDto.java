package br.com.tiagoiwamoto.profileadmin.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SoftwareDto {

    private Long id;
    private UUID uuid;
    private String name;
    private String description;
    private String url;
    private String urlMirror;
    private String pathOfImage;
    private String pathOfImageThumb;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
