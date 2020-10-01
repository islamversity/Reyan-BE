package com.islamversity.reyan.reyan.service;

import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

public interface ReciteService {
    byte[] getAyeRecite(Integer languageId, String surehId, String ayeId);
}
