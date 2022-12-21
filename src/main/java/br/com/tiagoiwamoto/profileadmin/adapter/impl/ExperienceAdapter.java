package br.com.tiagoiwamoto.profileadmin.adapter.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.AbstractAdapter;
import br.com.tiagoiwamoto.profileadmin.core.domain.ExperienceDomain;
import br.com.tiagoiwamoto.profileadmin.core.repository.ExperienceRepository;
import org.springframework.stereotype.Service;

@Service
public class ExperienceAdapter extends AbstractAdapter<ExperienceDomain> {

    public ExperienceAdapter(ExperienceRepository repository) {
        super(repository, ExperienceDomain.class.getSimpleName());
    }

}
