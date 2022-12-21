package br.com.tiagoiwamoto.profileadmin.adapter.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.AbstractAdapter;
import br.com.tiagoiwamoto.profileadmin.core.domain.ProjectDomain;
import br.com.tiagoiwamoto.profileadmin.core.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectAdapter extends AbstractAdapter<ProjectDomain> {

    public ProjectAdapter(ProjectRepository repository) {
        super(repository, ProjectDomain.class.getSimpleName());
    }

}
