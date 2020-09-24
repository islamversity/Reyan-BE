package com.islamversity.reyan.reyan.service;

import com.islamversity.reyan.reyan.model.AyeDTO;

import java.util.List;

public interface SimpleSearchService {
    List<String> findAllSurahsNames();

    List<AyeDTO> findAyehsBySurahName(String surah);
}

