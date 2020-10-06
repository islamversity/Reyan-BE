package com.islamversity.reyan.reyan.controller;

import com.islamversity.reyan.reyan.service.ReciteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Slf4j
@RestController
@RequestMapping("/api/recite")
@RequiredArgsConstructor
public class ReciteController {
    private final ReciteService reciteService;

    @RequestMapping(value = "/{languageId}/{surehId}/{ayeId}", method = GET)
    public void playAudio(@PathVariable("languageId") Integer languageId, @PathVariable("surehId") String surehId,
                          @PathVariable("ayeId") String ayeId,
                          HttpServletRequest request, HttpServletResponse response) {

        try {
            byte[] buffer = reciteService.getAyeRecite(languageId, surehId, ayeId);
            response.setContentType("audio");
            response.getOutputStream().write(buffer);
        } catch (IOException e) {
            log.error("Error while finding recite for languuageId:{}, surehId:{}, ayehId{}", languageId, surehId, ayeId, e);
        }

    }

    @RequestMapping(value = "/page/{reciterId}/{pageNumber}", method = GET)
    public void playAudio(@PathVariable("reciterId") Integer reciterId, @PathVariable("pageNumber") String pageNumber,
                          HttpServletRequest request, HttpServletResponse response) {

        try {
            log.info("Playing page {}", pageNumber);
            byte[] buffer = reciteService.getPage(reciterId, pageNumber);
            response.setContentType("audio");
            response.getOutputStream().write(buffer);
        } catch (IOException e) {
            log.error("Error while finding recite for reciter:{}, page{}", reciterId, pageNumber, e);
        }

    }

}
