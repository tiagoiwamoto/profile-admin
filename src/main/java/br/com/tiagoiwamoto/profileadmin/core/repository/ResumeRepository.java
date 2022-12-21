package br.com.tiagoiwamoto.profileadmin.core.repository;

import br.com.tiagoiwamoto.profileadmin.core.domain.ResumeDomain;

import java.util.Optional;
import java.util.UUID;

public interface ResumeRepository extends IRepository<ResumeDomain, Long> {

    Optional<ResumeDomain> findByUuid(UUID uuid);
}
