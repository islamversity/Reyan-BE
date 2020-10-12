package com.islamversity.reyan.reyan.repository;

import com.islamversity.reyan.reyan.domain.es.SurahDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurahEsRepository extends ElasticsearchRepository<SurahDocument, Integer>{
}
