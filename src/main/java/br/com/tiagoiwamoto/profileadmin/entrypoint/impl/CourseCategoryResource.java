package br.com.tiagoiwamoto.profileadmin.entrypoint.impl;

import br.com.tiagoiwamoto.profileadmin.core.usecase.IUsecaseCommon;
import br.com.tiagoiwamoto.profileadmin.entrypoint.AbstractResource;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.CourseCategoryDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/courses_categories")
public class CourseCategoryResource extends AbstractResource<CourseCategoryDto> {

    public CourseCategoryResource(IUsecaseCommon courseCategoryUsecase) {
        super(courseCategoryUsecase, "/courses_categories");
    }
}
