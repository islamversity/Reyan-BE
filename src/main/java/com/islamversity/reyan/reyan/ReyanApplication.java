package com.islamversity.reyan.reyan;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.islamversity.reyan.reyan.domain.Aye;
import com.islamversity.reyan.reyan.domain.Surah;
import com.islamversity.reyan.reyan.model.AyeDTO;
import com.islamversity.reyan.reyan.model.SurahDTO;
import com.islamversity.reyan.reyan.repository.AyeRepository;
import com.islamversity.reyan.reyan.repository.SurahRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootApplication
@Slf4j
public class ReyanApplication {
    private static final Integer HAMD_INDEX = 1;

    public static void main(String[] args) {
        SpringApplication.run(ReyanApplication.class, args);
    }

    @Bean
    CommandLineRunner feeding(SurahRepository surahRepository, AyeRepository ayeRepository) {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<SurahDTO>> typeReference = new TypeReference<>() {
            };
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/quran-simple-one-line.json");
            try {
                List<SurahDTO> surahDTOS = mapper.readValue(inputStream, typeReference);
                Optional<Surah> hamd = surahRepository.findSurahByIndex(HAMD_INDEX);
                if (hamd.isPresent()){
                    log.info(""+ hamd);
                    return;
                }
                    surahDTOS.stream()
                            .map(surahDTO -> makeSurahDomain(surahDTO, ayeRepository))
                            .forEach(surah -> persistSurah(surahRepository, surah));
            } catch (Exception e) {
                log.error("Problem in database initialization", e);
            }
        };
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
