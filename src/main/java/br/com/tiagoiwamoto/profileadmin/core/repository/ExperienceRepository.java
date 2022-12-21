package br.com.tiagoiwamoto.profileadmin.core.repository;

import br.com.tiagoiwamoto.profileadmin.core.domain.ExperienceDomain;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ExperienceRepository extends IRepository<ExperienceDomain, Long> {

    Optional<ExperienceDomain> findByUuid(UUID uuid);
}
