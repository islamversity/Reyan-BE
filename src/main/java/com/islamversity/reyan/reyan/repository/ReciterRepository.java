package com.islamversity.reyan.reyan.repository;

import com.islamversity.reyan.reyan.domain.Reciter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReciterRepository extends CrudRepository<Reciter, Integer> {
}
