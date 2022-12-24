package br.com.tiagoiwamoto.profileadmin.entrypoint.impl;

import br.com.tiagoiwamoto.profileadmin.core.usecase.IUsecaseCreateUpdate;
import br.com.tiagoiwamoto.profileadmin.entrypoint.AbstractResource;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.SkillDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/skills")
public class SkillResource extends AbstractResource<SkillDto> {

    public SkillResource(IUsecaseCreateUpdate skillUsecase) {
        super(skillUsecase, "/skills");
    }

}
