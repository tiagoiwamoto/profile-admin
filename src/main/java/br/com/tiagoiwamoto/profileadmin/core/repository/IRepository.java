package br.com.tiagoiwamoto.profileadmin.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface IRepository<T, Long> extends JpaRepository<T, Long> {

    Optional<T> findByUuid(UUID uuid);

}
