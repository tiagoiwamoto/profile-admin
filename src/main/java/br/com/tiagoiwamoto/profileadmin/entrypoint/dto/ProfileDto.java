package br.com.tiagoiwamoto.profileadmin.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDto extends AbstractDto{

    private String name;
    private String title;
    private String subTitle;
    private String email;
    private String phone;
    private Boolean isActive;
    private String description;

}
