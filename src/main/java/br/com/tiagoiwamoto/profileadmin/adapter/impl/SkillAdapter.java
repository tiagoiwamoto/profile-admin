package br.com.tiagoiwamoto.profileadmin.adapter.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.AbstractAdapter;
import br.com.tiagoiwamoto.profileadmin.core.domain.SkillDomain;
import br.com.tiagoiwamoto.profileadmin.core.repository.SkillRepository;
import org.springframework.stereotype.Service;

@Service
public class SkillAdapter extends AbstractAdapter<SkillDomain> {

    public SkillAdapter(SkillRepository repository) {
        super(repository, SkillDomain.class.getSimpleName());
    }

}
