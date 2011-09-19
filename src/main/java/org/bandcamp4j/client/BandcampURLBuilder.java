package org.bandcamp4j.client;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;

public class BandcampURLBuilder {

    public String buildUrl(String baseUrl, String apiKey, Object[] arguments) {
        String nameParams = toUrlEncodedCommaSeparatedList(arguments);
        return MessageFormat.format(baseUrl, apiKey, nameParams);
    }

    public static String toUrlEncodedCommaSeparatedList(Object[] objects) {
        StringBuilder params = new StringBuilder();
        for (Object object : objects) {
            try {
                params.append(URLEncoder.encode(object.toString(), "UTF-8"));
                params.append(',');
            } catch (UnsupportedEncodingException e) {
                // this shouldn't happen
            }
        }
        return params.substring(0, params.length() - 1);
    }
}
