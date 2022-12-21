package br.com.tiagoiwamoto.profileadmin.adapter.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.AbstractAdapter;
import br.com.tiagoiwamoto.profileadmin.core.domain.ScholarityDomain;
import br.com.tiagoiwamoto.profileadmin.core.repository.ScholarityRepository;
import org.springframework.stereotype.Service;

@Service
public class ScholarityAdapter extends AbstractAdapter<ScholarityDomain> {

    public ScholarityAdapter(ScholarityRepository repository) {
        super(repository, ScholarityDomain.class.getSimpleName());
    }

}
