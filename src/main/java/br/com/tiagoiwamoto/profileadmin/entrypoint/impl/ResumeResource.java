package br.com.tiagoiwamoto.profileadmin.entrypoint.impl;

import br.com.tiagoiwamoto.profileadmin.core.usecase.impl.ResumeUsecase;
import br.com.tiagoiwamoto.profileadmin.entrypoint.AbstractResource;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.ResumeDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/resumes")
public class ResumeResource extends AbstractResource<ResumeDto> {

    public ResumeResource(ResumeUsecase resumeUsecase) {
        super(resumeUsecase, "/resumes");
    }

}
