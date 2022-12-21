package br.com.tiagoiwamoto.profileadmin.entrypoint;

import br.com.tiagoiwamoto.profileadmin.core.usecase.impl.CourseCategoryUsecase;
import br.com.tiagoiwamoto.profileadmin.entrypoint.dto.CourseCategoryDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/courses_categories")
public class CourseCategoryResource extends AbstractResource<CourseCategoryDto>{

    public CourseCategoryResource(CourseCategoryUsecase courseCategoryUsecase) {
        super(courseCategoryUsecase, "/courses_categories");
    }
}
