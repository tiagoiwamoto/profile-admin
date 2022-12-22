package br.com.tiagoiwamoto.profileadmin.core.mapper;

import br.com.tiagoiwamoto.profileadmin.core.domain.CourseCategoryDomain;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.CourseCategoryDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CourseCategoryMapper implements IMapper<CourseCategoryDomain, CourseCategoryDto>{

    public CourseCategoryDomain toDomain(CourseCategoryDto courseCategoryDto){
        CourseCategoryDomain courseCategoryDomain = new CourseCategoryDomain();
        BeanUtils.copyProperties(courseCategoryDto, courseCategoryDomain);
        return courseCategoryDomain;
    }

    public CourseCategoryDto toDto(CourseCategoryDomain courseCategoryDomain){
        CourseCategoryDto courseCategoryDto = new CourseCategoryDto();
        BeanUtils.copyProperties(courseCategoryDomain, courseCategoryDto);
        return courseCategoryDto;
    }

}