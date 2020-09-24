package com.islamversity.reyan.reyan.repository;

import com.islamversity.reyan.reyan.domain.Aye;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AyeRepository extends CrudRepository<Aye, Integer> {
    List<Aye> findBySurahIndex(Integer surahIndex);

}
