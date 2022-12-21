package br.com.tiagoiwamoto.profileadmin.core.mapper;

import br.com.tiagoiwamoto.profileadmin.core.domain.ExperienceDomain;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ExperienceDto;
import org.springframework.beans.BeanUtils;

public class ExperienceMapper {

    public ExperienceDomain toDomain(ExperienceDto experienceDto){
        ExperienceDomain experienceDomain = new ExperienceDomain();
        BeanUtils.copyProperties(experienceDto, experienceDomain);
        return experienceDomain;
    }

    public ExperienceDto toDto(ExperienceDomain experienceDomain){
        ExperienceDto experienceDto = new ExperienceDto();
        BeanUtils.copyProperties(experienceDomain, experienceDto);
        return experienceDto;
    }

}
