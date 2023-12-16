package com.urlShortner.URLShortner.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class AppUtil {


    public String generateShortKey(String longUrl) {
        if(longUrl == null) return "";
        longUrl = getWebsiteName(longUrl);
        if (longUrl.length() <= 2) {
            return longUrl.toLowerCase();
        }

        String shortUrl = "";

        shortUrl += (longUrl.substring(0, 2) + longUrl.substring(longUrl.length() - 1)).toLowerCase();

        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            shortUrl += String.valueOf(random.nextInt(10));
        }
        return shortUrl;
    }

    private static String getWebsiteName(String websiteName) {
        websiteName = websiteName.toLowerCase();
        if (websiteName.contains("http") || websiteName.contains("www")) {
            websiteName = websiteName.substring(websiteName.indexOf(".") + 1);
        }
        return websiteName;
    }
}
