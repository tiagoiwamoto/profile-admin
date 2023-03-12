package br.com.tiagoiwamoto.profileadmin.core.mapper;

import br.com.tiagoiwamoto.profileadmin.core.domain.CourseCategoryDomain;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.CourseCategoryDto;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.CourseDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

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

        List<CourseDto> courses = mapper().convertValue(courseCategoryDomain.getCourses(), new TypeReference<>() {});
        courseCategoryDto.setCourses(courses);
        return courseCategoryDto;
    }

    public static ObjectMapper mapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        return mapper;
    }

}
