package br.com.tiagoiwamoto.profileadmin.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseDto extends AbstractDtoWithImage{

    private String name;
    private String school;
    private Integer duration;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private CourseCategoryDto courseCategory;
    private UUID courseCategoryUuid;

}
