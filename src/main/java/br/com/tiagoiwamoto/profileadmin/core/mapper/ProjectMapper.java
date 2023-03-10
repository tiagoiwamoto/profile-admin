package br.com.tiagoiwamoto.profileadmin.core.mapper;

import br.com.tiagoiwamoto.profileadmin.core.domain.ProjectDomain;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ProjectDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper implements IMapper<ProjectDomain, ProjectDto>{

    public ProjectDomain toDomain(ProjectDto projectDto){
        ProjectDomain projectDomain = new ProjectDomain();
        BeanUtils.copyProperties(projectDto, projectDomain);
        return projectDomain;
    }

    public ProjectDto toDto(ProjectDomain projectDomain){
        ProjectDto projectDto = new ProjectDto();
        BeanUtils.copyProperties(projectDomain, projectDto);
        return projectDto;
    }

}
