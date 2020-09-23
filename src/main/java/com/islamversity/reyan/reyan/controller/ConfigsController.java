package com.islamversity.reyan.reyan.controller;

import com.islamversity.reyan.reyan.model.ConfigDTO;
import com.islamversity.reyan.reyan.service.ConfigsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/config")
@RequiredArgsConstructor
public class ConfigsController {
    private final ConfigsService configsService;

    @PostMapping()
    public ResponseEntity<Integer> addConfigs(@RequestBody ConfigDTO configDTO) {
        return ResponseEntity.ok(configsService.addConfig(configDTO));
    }

    @GetMapping("/allconfigs")
    public ResponseEntity<List<ConfigDTO>> getAllConfigs() {
        return ResponseEntity.ok(configsService.getAllConfigs());
    }
}
