package br.com.tiagoiwamoto.profileadmin.core.repository;

import br.com.tiagoiwamoto.profileadmin.core.domain.ProfileDomain;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfileRepository extends IRepository<ProfileDomain, Long> {

    Optional<ProfileDomain> findByUuid(UUID uuid);

}
