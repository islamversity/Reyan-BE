package com.islamversity.reyan.reyan.service.impl;

import com.islamversity.reyan.reyan.domain.Config;
import com.islamversity.reyan.reyan.model.ConfigDTO;
import com.islamversity.reyan.reyan.repository.ConfigsRepository;
import com.islamversity.reyan.reyan.service.ConfigsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConfigServiceImpl implements ConfigsService {
    private final ConfigsRepository configsRepository;

    @Override
    public Integer addConfig(ConfigDTO configDTO) {
        Config persistedConfig = configsRepository.save(convert(configDTO));
        return persistedConfig.getIndex();
    }

    @Override
    public List<ConfigDTO> getAllConfigs() {
        List<ConfigDTO> configs = new ArrayList<>();
        configsRepository.findAll().iterator().forEachRemaining(c -> configs.add(toDTO(c)));
        return configs;
    }

    private ConfigDTO toDTO(Config config) {
        return ConfigDTO.builder()
                .description(config.getDescription())
                .forceUpdate(config.getForceUpdate())
                .index(config.getIndex())
                .version(config.getVersion())
                .build();
    }

    private Config convert(ConfigDTO configDTO) {
        return Config.builder()
                .index(configDTO.getIndex())
                .description(configDTO.getDescription())
                .forceUpdate(configDTO.getForceUpdate())
                .version(configDTO.getVersion())
                .build();
    }
}
