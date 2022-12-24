package br.com.tiagoiwamoto.profileadmin.core.usecase.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.impl.SkillAdapter;
import br.com.tiagoiwamoto.profileadmin.core.mapper.SkillMapper;
import br.com.tiagoiwamoto.profileadmin.core.usecase.AbstractUsecase;
import br.com.tiagoiwamoto.profileadmin.core.usecase.IUsecaseCreateUpdate;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.AbstractDto;
import org.springframework.stereotype.Service;

@Service
public class SkillUsecase extends AbstractUsecase implements IUsecaseCreateUpdate<AbstractDto> {

    public SkillUsecase(SkillAdapter adapter, SkillMapper mapper) {
        super(adapter, mapper);
    }

}
