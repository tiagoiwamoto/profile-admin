package br.com.tiagoiwamoto.profileadmin.adapter.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.AbstractAdapter;
import br.com.tiagoiwamoto.profileadmin.core.domain.SoftwareDomain;
import br.com.tiagoiwamoto.profileadmin.core.repository.SoftwareRepository;
import org.springframework.stereotype.Service;

@Service
public class SoftwareAdapter extends AbstractAdapter<SoftwareDomain> {

    public SoftwareAdapter(SoftwareRepository repository) {
        super(repository, SoftwareDomain.class.getSimpleName());
    }

}
