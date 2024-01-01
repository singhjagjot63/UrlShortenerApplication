package com.urlShortner.URLShortner.service;

import com.urlShortner.URLShortner.util.AppUtil;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled
public class UrlShortenerTest {

    @Autowired
    UrlShortenerService urlShortenerService;

    @Mock
    AppUtil appUtil;

    @Test
    public void testUrlShortener() {
        String url = "https://www.youtube.com/watch?v=spZUXAeXuOc&list=RDKNXYonYD59w&index=3&ab_channel=ZeeMusicCompany";
        urlShortenerService.fetchShortenedUrl(url);
    }
}
