package br.com.tiagoiwamoto.profileadmin.core.repository;

import br.com.tiagoiwamoto.profileadmin.core.domain.ScholarityDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ScholarityRepository extends JpaRepository<ScholarityDomain, Long> {

    Optional<ScholarityDomain> findByUuid(UUID uuid);
}
