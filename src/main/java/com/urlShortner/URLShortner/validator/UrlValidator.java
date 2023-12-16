package com.urlShortner.URLShortner.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlValidator {

    public static boolean validateUrl(String url) {
        String regex = "((http|https)://)(www.)?"
                + "[a-zA-Z0-9@:%._\\+~#?&//=]"
                + "{2,256}\\.[a-z]"
                + "{2,6}\\b([-a-zA-Z0-9@:%"
                + "._\\+~#?&//=]*)";

        Pattern p = Pattern.compile(regex);

        if (null == url) {
            return false;
        }
        Matcher m = p.matcher(url);
        return m.matches();
    }
}
