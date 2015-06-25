package org.bandcamp4j.client;

import java.io.*;
import java.net.URL;

/**
 * Simple implementation that just uses java.net.URL.
 */
public class BandcampClientConnectionImpl implements BandcampClientConnection {
    public Reader getReader(String url) throws IOException {
        URL bandSearchUrl = new URL(url);
        InputStream inputStream = bandSearchUrl.openStream();
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    public String getJson(String url) throws IOException {
        URL urlObject = new URL(url);

        InputStream inputStream = urlObject.openStream();
        Reader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringWriter writer = new StringWriter();

        char[] buffer = new char[128];
        int charsRead;
        try {
            while ((charsRead = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, charsRead);
            }
        } finally {
            reader.close();
        }

        return writer.toString();
    }
}
