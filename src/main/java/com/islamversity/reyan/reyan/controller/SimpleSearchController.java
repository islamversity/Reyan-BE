package com.islamversity.reyan.reyan.controller;

import com.islamversity.reyan.reyan.model.SurahDTO;
import com.islamversity.reyan.reyan.service.SimpleSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SimpleSearchController {
    private final SimpleSearchService simpleSearchService;

    @GetMapping("/allsurahs")
    ResponseEntity<List<String>> allSurahsList() {
        return ResponseEntity.ok(simpleSearchService.findAllSurahsNames());

    }

}
