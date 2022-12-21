package br.com.tiagoiwamoto.profileadmin.entrypoint.impl;

import br.com.tiagoiwamoto.profileadmin.core.usecase.impl.ExperienceUsecase;
import br.com.tiagoiwamoto.profileadmin.entrypoint.AbstractResource;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ExperienceDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/experiences")
public class ExperienceResource extends AbstractResource<ExperienceDto> {

    public ExperienceResource(ExperienceUsecase experienceUsecase) {
        super(experienceUsecase, "/experiences");
    }
}
