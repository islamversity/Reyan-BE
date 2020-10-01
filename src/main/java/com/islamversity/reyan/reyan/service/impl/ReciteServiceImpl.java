package com.islamversity.reyan.reyan.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.islamversity.reyan.reyan.domain.Language;
import com.islamversity.reyan.reyan.exception.NotFoundException;
import com.islamversity.reyan.reyan.repository.LanguagesRepository;
import com.islamversity.reyan.reyan.service.ReciteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReciteServiceImpl implements ReciteService {
    private final LanguagesRepository languagesRepository;

    @Override
    public byte[] getAyeRecite(Integer languageId, String surehId, String ayeId) {
        byte[] buffer = null;
        String local = getLocal(languageId);
        String recite = "/sounds/" + local + "/" + surehId + ayeId + ".mp3";
        log.info("recite: {} ", recite);

        InputStream inputStream = TypeReference.class.getResourceAsStream(recite);
        try {
            if (inputStream == null) {
                throw new NotFoundException();
            }
            buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
        } catch (FileNotFoundException e) {
            log.error("File Not Found", e);
            throw new NotFoundException("File Not Found");
        } catch (IOException e) {
            log.error("IOException while reading recite {} ", recite, e);
        }
        return buffer;
    }

    private String getLocal(Integer languageId) {
        Optional<Language> language = languagesRepository.findById(languageId);
        if (!language.isPresent()) {
            log.error("LanguageId {} not found.", language);
            throw new NotFoundException();
        }
        return language.get().getAbbreviation();
    }
}
