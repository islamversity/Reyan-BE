package com.islamversity.reyan.reyan.repository;

import com.islamversity.reyan.reyan.domain.Config;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfigsRepository extends CrudRepository<Config, Integer> {
    Optional<Config> findConfigsByVersion(String version);

    Optional<Config> findConfigsByForceUpdate(String forceUpdate);

}
