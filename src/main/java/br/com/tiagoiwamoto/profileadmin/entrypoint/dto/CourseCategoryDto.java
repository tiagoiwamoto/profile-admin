package br.com.tiagoiwamoto.profileadmin.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseCategoryDto extends AbstractDto{

    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}