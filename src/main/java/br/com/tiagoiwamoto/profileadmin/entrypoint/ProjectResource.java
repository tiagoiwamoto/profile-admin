package br.com.tiagoiwamoto.profileadmin.entrypoint;

import br.com.tiagoiwamoto.profileadmin.core.usecase.impl.ProjectUsecase;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ProjectDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/projects")
public class ProjectResource extends AbstractResource<ProjectDto>{

    public ProjectResource(ProjectUsecase projectUsecase) {
        super(projectUsecase, "/projects");
    }
}
