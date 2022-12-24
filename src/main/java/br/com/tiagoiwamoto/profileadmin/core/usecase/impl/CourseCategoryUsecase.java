package br.com.tiagoiwamoto.profileadmin.core.usecase.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.impl.CourseCategoryAdapter;
import br.com.tiagoiwamoto.profileadmin.core.mapper.CourseCategoryMapper;
import br.com.tiagoiwamoto.profileadmin.core.usecase.AbstractUsecase;
import br.com.tiagoiwamoto.profileadmin.core.usecase.IUsecaseCreateUpdate;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.AbstractDto;
import org.springframework.stereotype.Service;

@Service
public class CourseCategoryUsecase extends AbstractUsecase implements IUsecaseCreateUpdate<AbstractDto> {

    public CourseCategoryUsecase(CourseCategoryAdapter adapter, CourseCategoryMapper mapper) {
        super(adapter, mapper);
    }


}
