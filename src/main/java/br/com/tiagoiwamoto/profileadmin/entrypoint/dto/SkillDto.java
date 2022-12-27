package br.com.tiagoiwamoto.profileadmin.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SkillDto extends AbstractDto{

    @NotNull
    @NotEmpty
    private String category;
    @NotNull
    @NotEmpty
    private String habilities;

}
