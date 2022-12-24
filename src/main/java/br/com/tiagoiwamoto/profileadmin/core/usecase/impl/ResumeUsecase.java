package br.com.tiagoiwamoto.profileadmin.core.usecase.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.impl.ResumeAdapter;
import br.com.tiagoiwamoto.profileadmin.core.mapper.ResumeMapper;
import br.com.tiagoiwamoto.profileadmin.core.usecase.AbstractUsecase;
import br.com.tiagoiwamoto.profileadmin.core.usecase.IUsecaseCreateUpdate;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.AbstractDto;
import org.springframework.stereotype.Service;

@Service
public class ResumeUsecase extends AbstractUsecase implements IUsecaseCreateUpdate<AbstractDto> {
    public ResumeUsecase(ResumeAdapter adapter, ResumeMapper mapper) {
        super(adapter, mapper);
    }

}
