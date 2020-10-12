package com.islamversity.reyan.reyan.controller;

import com.islamversity.reyan.reyan.domain.es.SurahDocument;
import com.islamversity.reyan.reyan.service.ElasticSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/es")
@RequiredArgsConstructor
public class EsController {
    private final ElasticsearchOperations elasticsearchOperations;
    private final ElasticSearchService elasticSearchService;

    @PostMapping("/surah")
    public ResponseEntity<String> save(@RequestBody SurahDocument surah) {

        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(surah.getIndex())
                .withObject(surah)
                .build();

        //String documentId = elasticsearchOperations.index(indexQuery);
        //return documentId;
        return ResponseEntity.ok("");
    }

    @GetMapping("/createIndexBulk")
    public ResponseEntity<String> createIndexBulk() {

        return ResponseEntity.ok(elasticSearchService.createBulkIndex());
    }



}
