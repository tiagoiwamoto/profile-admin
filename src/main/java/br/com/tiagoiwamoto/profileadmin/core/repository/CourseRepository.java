package br.com.tiagoiwamoto.profileadmin.core.repository;

import br.com.tiagoiwamoto.profileadmin.core.domain.CourseCategoryDomain;
import br.com.tiagoiwamoto.profileadmin.core.domain.CourseDomain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseRepository extends IRepository<CourseDomain, Long> {

    Optional<CourseDomain> findByUuid(UUID uuid);
    List<CourseDomain> findAllByCourseCategory(CourseCategoryDomain courseCategoryDomain);
}
