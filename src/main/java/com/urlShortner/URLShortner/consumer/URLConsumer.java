package com.urlShortner.URLShortner.consumer;

import com.urlShortner.URLShortner.service.UrlShortenerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("url/compress")
public class URLConsumer {

    @Autowired
    UrlShortenerService urlShortenerService;

    @PostMapping("${get.request.path}")
    public void generateShortUrl(@RequestBody JSONObject url,
                                 HttpServletRequest httpServletRequest) {
        urlShortenerService.setServletRequest(httpServletRequest);
        urlShortenerService.fetchShortenedUrl(url.get("longUrl").toString());
    }

    @GetMapping("{key}")
    public ModelAndView redirectToLongUrl(@PathVariable("key") String key,
                                          HttpServletRequest httpServletRequest,
                                          HttpServletResponse httpServletResponse) {
        String header = (httpServletRequest.getHeader("host"));
        String uri =  httpServletRequest.getRequestURI().substring(httpServletRequest.getRequestURI().indexOf("/"), httpServletRequest.getRequestURI().lastIndexOf("/"));
        String shortUrl = header + uri + "/" + key;
        urlShortenerService.setServletRequest(httpServletRequest);
        String longUrl = urlShortenerService.getLongUrl(shortUrl);
        return new ModelAndView("redirect:" + longUrl);
    }
}
