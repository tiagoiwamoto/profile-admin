package br.com.tiagoiwamoto.profileadmin.core.mapper;

import br.com.tiagoiwamoto.profileadmin.core.domain.CourseDomain;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.CourseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;

@RequiredArgsConstructor
public class CourseMapper {

    private final CourseCategoryMapper courseCategoryMapper;

    public CourseDomain toDomain(CourseDto courseDto){
        CourseDomain courseDomain = new CourseDomain();
        BeanUtils.copyProperties(courseDto, courseDomain);
        return courseDomain;
    }

    public CourseDto toDto(CourseDomain courseDomain){
        CourseDto courseDto = new CourseDto();
        BeanUtils.copyProperties(courseDomain, courseDto);
        courseDto.setCourseCategory(this.courseCategoryMapper.toDto(courseDomain.getCourseCategory()));
        return courseDto;
    }

}
