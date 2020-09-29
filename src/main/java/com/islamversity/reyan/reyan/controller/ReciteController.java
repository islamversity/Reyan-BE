package com.islamversity.reyan.reyan.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.islamversity.reyan.reyan.service.ReciteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Slf4j
@RestController
@RequestMapping("/api/recite")
@RequiredArgsConstructor
public class ReciteController {
    private final ReciteService reciteService;

    @RequestMapping(value = "/{local}/{ayeIndex}", method = GET)
    public void playAudio(@PathVariable("local") String local, @PathVariable("ayeIndex") String ayeIndex, HttpServletRequest request, HttpServletResponse response) {

        try {
            byte[] buffer = reciteService.getAyeRecite(local, ayeIndex);
            response.setContentType("audio");
            response.getOutputStream().write(buffer);
        } catch (IOException e) {
            log.error("Error while finding recite for local:{} , aye:{}", local, ayeIndex,e);
        }

    }

}
