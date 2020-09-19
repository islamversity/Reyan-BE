package com.islamversity.reyan.reyan.service.impl;

import com.islamversity.reyan.reyan.domain.Surah;
import com.islamversity.reyan.reyan.model.SurahDTO;
import com.islamversity.reyan.reyan.repository.SurahRepository;
import com.islamversity.reyan.reyan.service.SimpleSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SimpleSearchServiceImpl implements SimpleSearchService {
    private final SurahRepository surahRepository;

    @Override
    public List<String> findAllSurahsNames() {
        List<Surah> surahs = new ArrayList<>();
        surahRepository.findAll().iterator().forEachRemaining(surahs::add);
        return toNames(surahs);
    }

    private List<String> toNames(List<Surah> surahs) {
       return  surahs.stream()
                .map(surah -> surah.getName())
                .collect(Collectors.toList());
    }

}
