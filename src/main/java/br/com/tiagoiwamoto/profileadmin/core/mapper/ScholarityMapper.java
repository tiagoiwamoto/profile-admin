package br.com.tiagoiwamoto.profileadmin.core.mapper;

import br.com.tiagoiwamoto.profileadmin.core.domain.ScholarityDomain;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ScholarityDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ScholarityMapper {

    public ScholarityDomain toDomain(ScholarityDto scholarityDto){
        ScholarityDomain scholarityDomain = new ScholarityDomain();
        BeanUtils.copyProperties(scholarityDto, scholarityDomain);
        return scholarityDomain;
    }

    public ScholarityDto toDto(ScholarityDomain scholarityDomain){
        ScholarityDto scholarityDto = new ScholarityDto();
        BeanUtils.copyProperties(scholarityDomain, scholarityDto);
        return scholarityDto;
    }

}
