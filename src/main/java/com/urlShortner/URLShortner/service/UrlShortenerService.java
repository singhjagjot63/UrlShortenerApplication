package com.urlShortner.URLShortner.service;

import com.urlShortner.URLShortner.domain.Shorturl;
import com.urlShortner.URLShortner.repository.ShortenedUrlRepository;
import com.urlShortner.URLShortner.util.AppUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static org.apache.logging.log4j.util.Strings.EMPTY;
import static org.apache.logging.log4j.util.Strings.isEmpty;

@Service
public class UrlShortenerService {

    private String serviceUrl;
    private HttpServletRequest httpServletRequest;

    @Value("${get.request.path}")
    String getMappingRequestPath;

    @Autowired
    private ShortenedUrlRepository shortenedUrlRepository;

    @Autowired
    private AppUtil appUtil;

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
        this.serviceUrl = httpServletRequest.getHeader("host") + httpServletRequest.getRequestURI().split(getMappingRequestPath)[0];

    }

    public void fetchShortenedUrl(String longUrl) {
       // serviceUrl = "host/shorten";
        Shorturl shortenedUrl = shortenedUrlRepository.getShortUrl(longUrl);
        if(null != shortenedUrl) {
            return;
        }
        String shortUrlKey = appUtil.generateShortKey(longUrl);
        String savedShortUrl = serviceUrl + "/" + shortUrlKey;

        Shorturl shorturl = new Shorturl();
        shorturl.setLongUrl(longUrl);
        shorturl.setShortUrl(savedShortUrl);

        shortenedUrlRepository.save(shorturl);
    }

    public String getLongUrl(String shortUrl) {
        String urlLong = shortenedUrlRepository.fetchLongUrl(shortUrl);
        if(isEmpty(urlLong)) {
            return EMPTY;
        }
        return urlLong;
    }
}
