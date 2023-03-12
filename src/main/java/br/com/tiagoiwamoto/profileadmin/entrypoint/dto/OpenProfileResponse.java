package br.com.tiagoiwamoto.profileadmin.entrypoint.dto;

import br.com.tiagoiwamoto.profileadmin.core.repository.out.CourseMetric;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OpenProfileResponse {

    private List<AbstractDtoWithImage> certifications;
    private List<AbstractDto> coursesCategories;
    private List<CourseDto> latestCourses;
    private List<AbstractDto> experiences;
    private List<AbstractDto> profiles;
    private List<AbstractDto> projects;
    private List<AbstractDto> resumes;
    private List<AbstractDtoWithImage> scholarities;
    private List<AbstractDto> skills;
    private List<AbstractDtoWithImage> softwares;
    private List<CourseMetric> metrics;

}
