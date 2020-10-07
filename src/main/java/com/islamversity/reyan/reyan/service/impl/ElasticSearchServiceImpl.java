package com.islamversity.reyan.reyan.service.impl;

import com.islamversity.reyan.reyan.domain.es.SurahDocument;
import com.islamversity.reyan.reyan.repository.SurahEsRepository;
import com.islamversity.reyan.reyan.service.ElasticSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class ElasticSearchServiceImpl implements ElasticSearchService {
    private final SurahEsRepository surahEsRepository;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public SurahDocument save(SurahDocument surah) {
        return surahEsRepository.save(surah);
    }

    @Override
    public Iterable<SurahDocument> findAll() {
        return surahEsRepository.findAll();
    }
}
