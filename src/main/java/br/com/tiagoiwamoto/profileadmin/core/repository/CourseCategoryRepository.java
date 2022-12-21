package br.com.tiagoiwamoto.profileadmin.core.repository;

import br.com.tiagoiwamoto.profileadmin.core.domain.CourseCategoryDomain;

import java.util.Optional;
import java.util.UUID;

public interface CourseCategoryRepository extends IRepository<CourseCategoryDomain, Long>{

    Optional<CourseCategoryDomain> findByUuid(UUID uuid);
}
