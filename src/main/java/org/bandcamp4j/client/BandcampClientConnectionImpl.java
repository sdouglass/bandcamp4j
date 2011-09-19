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
}
