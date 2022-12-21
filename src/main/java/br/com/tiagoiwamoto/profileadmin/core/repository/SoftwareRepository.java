package br.com.tiagoiwamoto.profileadmin.core.repository;

import br.com.tiagoiwamoto.profileadmin.core.domain.SoftwareDomain;

import java.util.Optional;
import java.util.UUID;

public interface SoftwareRepository extends IRepository<SoftwareDomain, Long> {

    Optional<SoftwareDomain> findByUuid(UUID uuid);
}
