package br.com.tiagoiwamoto.profileadmin.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SkillDto {

    private Long id;
    private UUID uuid;
    private String category;
    private String habilities;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
