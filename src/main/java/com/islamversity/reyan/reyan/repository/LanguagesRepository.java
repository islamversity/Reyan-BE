package com.islamversity.reyan.reyan.repository;

import com.islamversity.reyan.reyan.domain.Language;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguagesRepository extends CrudRepository<Language, Integer> {
}
