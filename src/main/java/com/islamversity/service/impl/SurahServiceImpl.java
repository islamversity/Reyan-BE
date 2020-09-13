package com.islamversity.service.impl;

import com.islamversity.service.SurahService;
import com.islamversity.service.repository.SurahRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SurahServiceImpl implements SurahService {

    private final SurahRepository surahRepository;


}
