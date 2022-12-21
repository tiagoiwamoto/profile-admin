package br.com.tiagoiwamoto.profileadmin.core.repository;

import br.com.tiagoiwamoto.profileadmin.core.domain.ProjectDomain;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProjectRepository extends IRepository<ProjectDomain, Long> {

    Optional<ProjectDomain> findByUuid(UUID uuid);
}
