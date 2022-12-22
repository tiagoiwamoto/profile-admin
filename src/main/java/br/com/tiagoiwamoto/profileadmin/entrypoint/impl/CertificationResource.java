package br.com.tiagoiwamoto.profileadmin.entrypoint.impl;

import br.com.tiagoiwamoto.profileadmin.core.usecase.IUsecaseWithFile;
import br.com.tiagoiwamoto.profileadmin.core.usecase.impl.CertificationUsecase;
import br.com.tiagoiwamoto.profileadmin.entrypoint.AbstractResourceForFile;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.CertificationDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/certifications")
public class CertificationResource extends AbstractResourceForFile<CertificationDto> {

    public CertificationResource(IUsecaseWithFile certificationUsecase) {
        super(certificationUsecase, "/certifications");
    }
}
