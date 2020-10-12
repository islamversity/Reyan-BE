package com.islamversity.reyan.reyan.service.impl;

import com.islamversity.reyan.reyan.domain.Aye;
import com.islamversity.reyan.reyan.domain.es.SurahDocument;
import com.islamversity.reyan.reyan.repository.AyeRepository;
import com.islamversity.reyan.reyan.repository.SurahEsRepository;
import com.islamversity.reyan.reyan.service.ElasticSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Collections.singletonMap;
import static org.elasticsearch.action.support.WriteRequest.RefreshPolicy.IMMEDIATE;


@Slf4j
@Service
@RequiredArgsConstructor
public class ElasticSearchServiceImpl implements ElasticSearchService {
    private final SurahEsRepository surahEsRepository;
    private final RestHighLevelClient highLevelClient;
    private final AyeRepository ayeRepository;

    @Override
    public SurahDocument save(SurahDocument surah) {
        return surahEsRepository.save(surah);
    }

    @Override
    public Iterable<SurahDocument> findAll() {
        return surahEsRepository.findAll();
    }

    @Override
    public IndexResponse index() throws IOException {
        IndexRequest request = new IndexRequest("spring-data")
                .source(singletonMap("feature", "high-level-rest-client"))
                .setRefreshPolicy(IMMEDIATE);
        RequestOptions requestOptions = RequestOptions.DEFAULT;
        return highLevelClient.index(request, requestOptions);
    }

    @Override
    public String createBulkIndex() {
        List<String> ayat = new ArrayList<>();
        StringBuffer response = new StringBuffer();
        AtomicReference<Integer> _id = new AtomicReference<>(0);
        ayeRepository.findAll().iterator().forEachRemaining(aye -> ayat.add(makeBulkRespose(aye, _id.getAndSet(_id.get() + 1))));
        ayat.stream()
                .forEach(r-> response.append(r));

        return response.toString();
    }

    private String makeBulkRespose(Aye ayeh, Integer _id) {
        StringBuffer aye = new StringBuffer("\n{ \"create\" : { \"_index\": \"quran-farsi\", \"_id\" : \"" + _id + "\" } }");
        aye.append("\n{\"ayeh_index\": \"" + ayeh.getIndex() + "\", \"text\": \"" + ayeh.getText() + "\"," + "\"bismillah\": \"" + ayeh.getBismillah() + "\"" + "}");
        log.info("Aye: {}" + aye.toString());
        return aye.toString();

    }
}
