package com.islamversity.reyan.reyan.repository;

import com.islamversity.reyan.reyan.domain.Surah;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SurahRepository extends CrudRepository<Surah, Integer> {
    Optional<Surah> findSurahByIndex(Integer index);
}
