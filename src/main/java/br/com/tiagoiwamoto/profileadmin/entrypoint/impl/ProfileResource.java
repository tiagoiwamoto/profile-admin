package br.com.tiagoiwamoto.profileadmin.entrypoint.impl;

import br.com.tiagoiwamoto.profileadmin.core.usecase.IUsecaseCreateUpdate;
import br.com.tiagoiwamoto.profileadmin.entrypoint.AbstractResource;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ProfileDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/profiles")
public class ProfileResource extends AbstractResource<ProfileDto> {

    public ProfileResource(IUsecaseCreateUpdate profileUsecase) {
        super(profileUsecase, "/profiles");
    }
}
