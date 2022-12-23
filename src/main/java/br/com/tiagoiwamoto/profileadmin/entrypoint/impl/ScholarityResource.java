package br.com.tiagoiwamoto.profileadmin.entrypoint.impl;

import br.com.tiagoiwamoto.profileadmin.core.usecase.IUsecaseWithFile;
import br.com.tiagoiwamoto.profileadmin.entrypoint.AbstractResourceForFile;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ScholarityDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/scholarities")
public class ScholarityResource extends AbstractResourceForFile<ScholarityDto> {

    public ScholarityResource(IUsecaseWithFile scholarityUsecase) {
        super(scholarityUsecase,"/scholarities");
    }
}
