package com.islamversity.reyan.reyan.repository;

import com.islamversity.reyan.reyan.domain.es.SurahDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurahEsRepository extends ElasticsearchRepository<SurahDocument, Integer>{
    Page<SurahDocument> findByAuthorsName(String name, Pageable pageable);
}
