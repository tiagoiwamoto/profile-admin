package br.com.tiagoiwamoto.profileadmin.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CertificationDto {

    private Long id;
    private UUID uuid;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate earnDate;
    private String validateUrl;
    private String pathOfImage;
    private String pathOfImageThumb;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
