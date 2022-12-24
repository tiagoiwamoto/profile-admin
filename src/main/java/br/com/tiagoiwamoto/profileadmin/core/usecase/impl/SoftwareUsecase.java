package br.com.tiagoiwamoto.profileadmin.core.usecase.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.ImageAndThumbAdapter;
import br.com.tiagoiwamoto.profileadmin.adapter.impl.SoftwareAdapter;
import br.com.tiagoiwamoto.profileadmin.core.mapper.SoftwareMapper;
import br.com.tiagoiwamoto.profileadmin.core.usecase.AbstractImageUsecase;
import br.com.tiagoiwamoto.profileadmin.core.usecase.IUsecaseWithFile;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.AbstractDtoWithImage;
import org.springframework.stereotype.Service;

@Service
public class SoftwareUsecase extends AbstractImageUsecase implements IUsecaseWithFile<AbstractDtoWithImage> {

    public SoftwareUsecase(SoftwareAdapter adapter, SoftwareMapper mapper, ImageAndThumbAdapter imageAndThumbAdapter) {
        super(adapter, mapper, imageAndThumbAdapter, "files/softwares/");
    }

}
