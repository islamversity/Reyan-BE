package com.islamversity.reyan.reyan.service;

import com.islamversity.reyan.reyan.domain.Config;
import com.islamversity.reyan.reyan.model.ConfigDTO;

import java.util.List;

public interface ConfigsService {
    Integer addConfig(ConfigDTO configDTO);

    List<ConfigDTO> getAllConfigs();

}
