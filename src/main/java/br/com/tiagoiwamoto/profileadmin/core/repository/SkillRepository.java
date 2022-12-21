package br.com.tiagoiwamoto.profileadmin.core.repository;

import br.com.tiagoiwamoto.profileadmin.core.domain.SkillDomain;

import java.util.Optional;
import java.util.UUID;

public interface SkillRepository extends IRepository<SkillDomain, Long> {

    Optional<SkillDomain> findByUuid(UUID uuid);
}
