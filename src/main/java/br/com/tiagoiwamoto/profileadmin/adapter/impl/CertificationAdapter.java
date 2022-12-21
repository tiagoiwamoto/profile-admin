package br.com.tiagoiwamoto.profileadmin.adapter.impl;

import br.com.tiagoiwamoto.profileadmin.adapter.AbstractAdapter;
import br.com.tiagoiwamoto.profileadmin.core.domain.CertificationDomain;
import br.com.tiagoiwamoto.profileadmin.core.repository.CertificationRepository;
import org.springframework.stereotype.Service;

@Service
public class CertificationAdapter extends AbstractAdapter<CertificationDomain> {
    public CertificationAdapter(CertificationRepository repository) {
        super(repository, CertificationDomain.class.getSimpleName());
    }

}
