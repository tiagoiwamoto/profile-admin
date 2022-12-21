package br.com.tiagoiwamoto.profileadmin.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExperienceDto {

    private Long id;
    private UUID uuid;
    private String job;
    private String company;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;

}
