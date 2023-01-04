package br.com.tiagoiwamoto.profileadmin.entrypoint.impl;

import br.com.tiagoiwamoto.profileadmin.core.usecase.IUsecaseCreateUpdate;
import br.com.tiagoiwamoto.profileadmin.entrypoint.AbstractResource;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ProjectDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/projects")
public class ProjectResource extends AbstractResource<ProjectDto> {

    public ProjectResource(IUsecaseCreateUpdate projectUsecase) {
        super(projectUsecase, "/projects");
    }
}
