package com.islamversity.reyan.reyan.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.islamversity.reyan.reyan.service.ReciteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReciteServiceImpl implements ReciteService {
    @Override
    public byte[] getAyeRecite(String local, String ayeIndex) {
        String recite = "/sounds/" + local + "/" + ayeIndex + ".mp3";
        byte[] buffer = null;

        log.info("recite: {} ", recite);
        InputStream inputStream = TypeReference.class.getResourceAsStream(recite);
        try {
            buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
        } catch (FileNotFoundException e) {
            log.error("File Not Found", e);
        } catch (IOException e) {
            log.error("IOException while reading recite {} ", recite, e);
        }
        return buffer;
    }
}
