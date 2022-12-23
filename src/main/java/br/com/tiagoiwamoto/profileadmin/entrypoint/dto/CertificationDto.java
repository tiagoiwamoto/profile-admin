package br.com.tiagoiwamoto.profileadmin.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CertificationDto extends AbstractDtoWithImage{

    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate earnDate;
    private String validateUrl;
}
