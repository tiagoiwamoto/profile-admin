package br.com.tiagoiwamoto.profileadmin.core.repository;

import br.com.tiagoiwamoto.profileadmin.core.domain.SoftwareDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SoftwareRepository extends JpaRepository<SoftwareDomain, Long> {

    Optional<SoftwareDomain> findByUuid(UUID uuid);
}
