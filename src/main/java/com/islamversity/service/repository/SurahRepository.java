package com.islamversity.service.repository;

import com.islamversity.service.domain.Surah;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SurahRepository extends CrudRepository<Surah, Long> {
    Optional<Surah> findById(Long surahId);
}
