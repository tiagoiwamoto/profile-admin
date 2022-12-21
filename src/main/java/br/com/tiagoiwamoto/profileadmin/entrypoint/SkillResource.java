package br.com.tiagoiwamoto.profileadmin.entrypoint;

import br.com.tiagoiwamoto.profileadmin.core.usecase.impl.SkillUsecase;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.SkillDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/skills")
public class SkillResource extends AbstractResource<SkillDto>{

    public SkillResource(SkillUsecase skillUsecase) {
        super(skillUsecase, "/skills");
    }

}
