package br.com.tiagoiwamoto.profileadmin.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScholarityDto {

    private Long id;
    private UUID uuid;

    private String schoolName;
    private String courseName;
    private String titleReceivedCourse;
    private Integer duration;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfConclusion;

    private String pathOfCertificate;
    private String pathOfCertificateThumb;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
