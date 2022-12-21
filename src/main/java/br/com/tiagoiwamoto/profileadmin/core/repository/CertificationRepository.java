package br.com.tiagoiwamoto.profileadmin.core.repository;

import br.com.tiagoiwamoto.profileadmin.core.domain.CertificationDomain;

import java.util.Optional;
import java.util.UUID;

public interface CertificationRepository extends IRepository<CertificationDomain, Long> {

    Optional<CertificationDomain> findByUuid(UUID uuid);
}
