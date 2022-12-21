package br.com.tiagoiwamoto.profileadmin.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectDto {

    private Long id;
    private UUID uuid;
    private String name;
    private String description;
    private String url;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
