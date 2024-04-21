package com.berksprojects.urlshortener.service;

import com.opsmatters.bitly.Bitly;
import com.opsmatters.bitly.api.model.v4.CreateBitlinkRequest;
import com.opsmatters.bitly.api.model.v4.CreateBitlinkResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenerService {
    @Value("${BITLY_TOKEN}")
    String BITLY_TOKEN;
    private Bitly client;

    @PostConstruct
    public void setup() {
        client = new Bitly(BITLY_TOKEN);
    }
    public String getShortURL(String longURL) {
        String link = "error";

        try {
            CreateBitlinkResponse response = client.bitlinks().shorten(longURL).get();

            link = response.getLink();
        } catch (Exception ex) {
            System.out.println("Something was wrong: " + ex.getMessage());
        }

        return link;
    }
}
