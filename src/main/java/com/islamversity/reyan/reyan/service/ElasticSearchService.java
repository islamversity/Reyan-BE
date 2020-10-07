package com.islamversity.reyan.reyan.service;

import com.islamversity.reyan.reyan.domain.es.SurahDocument;

public interface ElasticSearchService {
    SurahDocument save(SurahDocument surah);

    Iterable<SurahDocument> findAll();
}
