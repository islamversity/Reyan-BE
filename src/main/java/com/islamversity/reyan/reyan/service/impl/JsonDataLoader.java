package com.islamversity.reyan.reyan.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.islamversity.reyan.reyan.domain.Aye;
import com.islamversity.reyan.reyan.domain.Language;
import com.islamversity.reyan.reyan.domain.Reciter;
import com.islamversity.reyan.reyan.domain.Surah;
import com.islamversity.reyan.reyan.model.AyeDTO;
import com.islamversity.reyan.reyan.model.SurahDTO;
import com.islamversity.reyan.reyan.repository.AyeRepository;
import com.islamversity.reyan.reyan.repository.LanguagesRepository;
import com.islamversity.reyan.reyan.repository.ReciterRepository;
import com.islamversity.reyan.reyan.repository.SurahRepository;
import com.islamversity.reyan.reyan.service.DataLoaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class JsonDataLoader implements DataLoaderService {

    private final ObjectMapper mapper = new ObjectMapper();
    private final ReciterRepository reciterRepository;
    private final LanguagesRepository languagesRepository;
    private final SurahRepository surahRepository;
    private final AyeRepository ayeRepository;

    @Override
    public void loadReciters() {
        TypeReference<List<Reciter>> typeReference = new TypeReference<>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/reciter.json");
        try {
            List<Reciter> reciters = mapper.readValue(inputStream, typeReference);
            if (reciterRepository.count() > 0) {
                log.info("Languages already loaded.");
                return;
            }
            reciters.stream()
                    .forEach(reciter -> reciterRepository.save(reciter));

        } catch (Exception e) {
            log.error("Problem loading reciters", e);
        }
    }

    @Override
    public void loadLanguages() {
        TypeReference<List<Language>> typeReference = new TypeReference<>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/languages.json");
        try {
            List<Language> languages = mapper.readValue(inputStream, typeReference);
            if (languagesRepository.count() > 0) {
                log.info("Reciters already loaded.");
                return;
            }
            languages.stream()
                    .forEach(language -> languagesRepository.save(language));

        } catch (Exception e) {
            log.error("Problem loading reciters", e);
        }

    }

    @Override
    public void loadQuran() {
        TypeReference<List<SurahDTO>> typeReference = new TypeReference<>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/quran-simple-one-line.json");
        try {
            List<SurahDTO> surahDTOS = mapper.readValue(inputStream, typeReference);
            if (surahRepository.count() > 0) {
                log.info("Quran already loaded.");
                return;
            }
            surahDTOS.stream()
                    .map(surahDTO -> makeSurahDomain(surahDTO, ayeRepository))
                    .forEach(surah -> persistSurah(surahRepository, surah));

        } catch (Exception e) {
            log.error("Problem in database initialization", e);
        }
    }

    private Surah makeSurahDomain(SurahDTO surahDTO, AyeRepository ayeRepository) {
        List<Aye> persistedAyehs = surahDTO.getAya().stream()
                .map(ayeDTO -> makeAyeDomain(surahDTO.getIndex(), ayeDTO))
                .map(aye -> persistAye(ayeRepository, aye))
                .collect(Collectors.toList());

        return Surah.builder()
                .ayat(persistedAyehs)
                .index(surahDTO.getIndex())
                .name(surahDTO.getName())
                .build();

    }

    private Surah persistSurah(SurahRepository surahRepository, Surah surah) {
        Surah persistedSurah = surahRepository.save(surah);
        return persistedSurah;

    }

    private Aye persistAye(AyeRepository ayeRepository, Aye aye) {
        Aye persistedAye = ayeRepository.save(aye);
        return persistedAye;
    }

    private Aye makeAyeDomain(Integer surahIndex, AyeDTO ayeDTO) {
        return Aye.builder()
                .bismillah(ayeDTO.getBismillah())
                .index(getAyeIndex(surahIndex, ayeDTO))
                .surahIndex(surahIndex)
                .text(ayeDTO.getText())
                .build()
                ;
    }

    private String getAyeIndex(Integer surahIndex, AyeDTO ayeDTO) {
        return surahIndex + "-" + ayeDTO.getIndex();
    }
}
