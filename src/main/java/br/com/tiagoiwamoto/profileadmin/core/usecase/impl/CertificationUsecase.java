package br.com.tiagoiwamoto.profileadmin.core.usecase.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.ImageAndThumbAdapter;
import br.com.tiagoiwamoto.profileadmin.adapter.impl.CertificationAdapter;
import br.com.tiagoiwamoto.profileadmin.core.mapper.CertificationMapper;
import br.com.tiagoiwamoto.profileadmin.core.usecase.AbstractImageUsecase;
import br.com.tiagoiwamoto.profileadmin.core.usecase.IUsecaseWithFile;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.AbstractDtoWithImage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CertificationUsecase extends AbstractImageUsecase implements IUsecaseWithFile<AbstractDtoWithImage> {

    public CertificationUsecase(CertificationAdapter adapter, CertificationMapper mapper, ImageAndThumbAdapter imageAndThumbAdapter) {
        super(adapter, mapper, imageAndThumbAdapter, "files/certifications/");
    }

}
