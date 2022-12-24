package br.com.tiagoiwamoto.profileadmin.core.usecase.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.ImageAndThumbAdapter;
import br.com.tiagoiwamoto.profileadmin.adapter.impl.ScholarityAdapter;
import br.com.tiagoiwamoto.profileadmin.core.mapper.ScholarityMapper;
import br.com.tiagoiwamoto.profileadmin.core.usecase.AbstractImageUsecase;
import br.com.tiagoiwamoto.profileadmin.core.usecase.IUsecaseWithFile;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.AbstractDtoWithImage;
import org.springframework.stereotype.Service;

@Service
public class ScholarityUsecase extends AbstractImageUsecase implements IUsecaseWithFile<AbstractDtoWithImage> {
    public ScholarityUsecase(ScholarityAdapter adapter, ScholarityMapper mapper, ImageAndThumbAdapter imageAndThumbAdapter) {
        super(adapter, mapper, imageAndThumbAdapter, "files/scholarity/");
    }

}
