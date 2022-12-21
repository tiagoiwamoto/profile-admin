package br.com.tiagoiwamoto.profileadmin.adapter.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.AbstractAdapter;
import br.com.tiagoiwamoto.profileadmin.core.domain.ResumeDomain;
import br.com.tiagoiwamoto.profileadmin.core.repository.ResumeRepository;
import org.springframework.stereotype.Service;

@Service
public class ResumeAdapter extends AbstractAdapter<ResumeDomain> {

    public ResumeAdapter(ResumeRepository repository) {
        super(repository, ResumeDomain.class.getSimpleName());
    }

}
