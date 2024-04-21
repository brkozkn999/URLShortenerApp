package com.berksprojects.urlshortener.controller;

import com.berksprojects.urlshortener.model.UrlShortenerModel;
import com.berksprojects.urlshortener.service.UrlShortenerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1")
public class UrlShortenerController {

    @Autowired
    UrlShortenerService urlShortenerService;

    @GetMapping("/")
    public String showForm() {
        return "index";
    }

    @PostMapping("/proccessBitly")
    public String proccessBitly(@RequestParam("longURL") String longURL, Model model) {
        String shortURL = urlShortenerService.getShortURL(longURL);
        model.addAttribute("shortURL", shortURL);
        return shortURL;
    }
}
