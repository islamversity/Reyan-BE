package com.islamversity.reyan.reyan.service.impl;

import com.islamversity.reyan.reyan.domain.Aye;
import com.islamversity.reyan.reyan.domain.Surah;
import com.islamversity.reyan.reyan.exception.NotFoundException;
import com.islamversity.reyan.reyan.model.AyeDTO;
import com.islamversity.reyan.reyan.model.SurahDTO;
import com.islamversity.reyan.reyan.repository.AyeRepository;
import com.islamversity.reyan.reyan.repository.SurahRepository;
import com.islamversity.reyan.reyan.service.SimpleSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SimpleSearchServiceImpl implements SimpleSearchService {
    private final SurahRepository surahRepository;
    private final AyeRepository ayeRepository;

    @Override
    public List<String> findAllSurahsNames() {
        List<Surah> surahs = new ArrayList<>();
        surahRepository.findAll().iterator().forEachRemaining(surahs::add);
        return toNames(surahs);
    }

    public List<AyeDTO> findAyehsBySurahName(String surah) {
        Surah surahByName = surahRepository.findSurahByName(surah).orElseThrow(NotFoundException::new);

        return ayeRepository.findBySurahIndex(surahByName.getIndex()).stream()
                .map(aye -> toDTO(aye))
                .collect(Collectors.toList());

    }

    private AyeDTO toDTO(Aye aye) {
        return AyeDTO.builder()
                .bismillah(aye.getBismillah())
                .index(findIndex(aye.getIndex()))
                .text(aye.getText())
                .build();
    }

    private Integer findIndex(String index) {
        String[] elements = index.split("-");
        return Integer.valueOf(elements[1]);
    }

    private List<String> toNames(List<Surah> surahs) {
        return surahs.stream()
                .map(surah -> surah.getName())
                .collect(Collectors.toList());
    }

}
