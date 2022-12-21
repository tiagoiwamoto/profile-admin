package br.com.tiagoiwamoto.profileadmin.core.mapper;

import br.com.tiagoiwamoto.profileadmin.core.domain.ScholarityDomain;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ScholarityDto;
import org.springframework.beans.BeanUtils;

public class ScholarityMapper {

    public ScholarityDomain toProfileDomain(ScholarityDto scholarityDto){
        ScholarityDomain scholarityDomain = new ScholarityDomain();
        BeanUtils.copyProperties(scholarityDto, scholarityDomain);
        return scholarityDomain;
    }

    public ScholarityDto toProfileDto(ScholarityDomain scholarityDomain){
        ScholarityDto scholarityDto = new ScholarityDto();
        BeanUtils.copyProperties(scholarityDomain, scholarityDto);
        return scholarityDto;
    }

}
