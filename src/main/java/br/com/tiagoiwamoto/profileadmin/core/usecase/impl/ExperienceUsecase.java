package br.com.tiagoiwamoto.profileadmin.core.usecase.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.impl.ExperienceAdapter;
import br.com.tiagoiwamoto.profileadmin.core.mapper.ExperienceMapper;
import br.com.tiagoiwamoto.profileadmin.core.usecase.AbstractUsecase;
import br.com.tiagoiwamoto.profileadmin.core.usecase.IUsecaseCommon;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.AbstractDto;
import org.springframework.stereotype.Service;

@Service
public class ExperienceUsecase extends AbstractUsecase implements IUsecaseCommon<AbstractDto> {
    public ExperienceUsecase(ExperienceAdapter adapter, ExperienceMapper mapper) {
        super(adapter, mapper);
    }

}
