package com.islamversity.reyan.reyan.service;

import com.islamversity.reyan.reyan.domain.es.SurahDocument;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;

import java.io.IOException;

public interface ElasticSearchService {
    SurahDocument save(SurahDocument surah);

    Iterable<SurahDocument> findAll();

    IndexResponse index() throws IOException;

    String createBulkIndex();
}
