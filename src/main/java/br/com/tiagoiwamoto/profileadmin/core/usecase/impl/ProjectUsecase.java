package br.com.tiagoiwamoto.profileadmin.core.usecase.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.impl.ProjectAdapter;
import br.com.tiagoiwamoto.profileadmin.core.mapper.ProjectMapper;
import br.com.tiagoiwamoto.profileadmin.core.usecase.AbstractUsecase;
import br.com.tiagoiwamoto.profileadmin.core.usecase.IUsecaseCreateUpdate;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.AbstractDto;
import org.springframework.stereotype.Service;

@Service
public class ProjectUsecase extends AbstractUsecase implements IUsecaseCreateUpdate<AbstractDto> {
    public ProjectUsecase(ProjectAdapter adapter, ProjectMapper mapper) {
        super(adapter, mapper);
    }

}
