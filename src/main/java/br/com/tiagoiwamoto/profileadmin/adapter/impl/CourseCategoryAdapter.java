package br.com.tiagoiwamoto.profileadmin.adapter.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.AbstractAdapter;
import br.com.tiagoiwamoto.profileadmin.core.domain.CourseCategoryDomain;
import br.com.tiagoiwamoto.profileadmin.core.repository.CourseCategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseCategoryAdapter extends AbstractAdapter<CourseCategoryDomain> {

    public CourseCategoryAdapter(CourseCategoryRepository repository) {
        super(repository, CourseCategoryDomain.class.getSimpleName());
    }

}
