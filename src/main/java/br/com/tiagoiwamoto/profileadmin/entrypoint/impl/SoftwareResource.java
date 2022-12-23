package br.com.tiagoiwamoto.profileadmin.entrypoint.impl;

import br.com.tiagoiwamoto.profileadmin.core.usecase.IUsecaseWithFile;
import br.com.tiagoiwamoto.profileadmin.entrypoint.AbstractResourceForFile;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.SoftwareDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/softwares")
public class SoftwareResource extends AbstractResourceForFile<SoftwareDto> {

    public SoftwareResource(IUsecaseWithFile softwareUsecase) {
        super(softwareUsecase, "/softwares");
    }

}
