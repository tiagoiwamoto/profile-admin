package br.com.tiagoiwamoto.profileadmin.core.usecase.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.impl.ProfileAdapter;
import br.com.tiagoiwamoto.profileadmin.core.mapper.ProfileMapper;
import br.com.tiagoiwamoto.profileadmin.core.usecase.AbstractUsecase;
import br.com.tiagoiwamoto.profileadmin.core.usecase.IUsecaseCreateUpdate;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.AbstractDto;
import org.springframework.stereotype.Service;

@Service
public class ProfileUsecase extends AbstractUsecase implements IUsecaseCreateUpdate<AbstractDto> {

    public ProfileUsecase(ProfileAdapter adapter, ProfileMapper mapper) {
        super(adapter, mapper);
    }


}
