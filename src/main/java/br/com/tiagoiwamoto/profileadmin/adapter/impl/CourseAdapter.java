package br.com.tiagoiwamoto.profileadmin.adapter.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.AbstractAdapter;
import br.com.tiagoiwamoto.profileadmin.core.domain.CourseCategoryDomain;
import br.com.tiagoiwamoto.profileadmin.core.domain.CourseDomain;
import br.com.tiagoiwamoto.profileadmin.core.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CourseAdapter extends AbstractAdapter<CourseDomain> {

    private CourseRepository repository;
    public CourseAdapter(CourseRepository repository) {
        super(repository, CourseDomain.class.getSimpleName());
        this.repository = repository;
    }

    public List<CourseDomain> all(CourseCategoryDomain courseCategoryDomain){

        log.info("iniciando busca: metodo: CourseAdapter.all(), para o dominio: " + CourseDomain.class.getSimpleName());
        try{
            return this.repository.findAllByCourseCategory(courseCategoryDomain);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
