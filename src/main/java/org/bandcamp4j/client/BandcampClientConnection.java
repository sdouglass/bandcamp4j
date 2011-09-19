package org.bandcamp4j.client;

import java.io.IOException;
import java.io.Reader;

/**
 * Handles connecting to Bandcamp.
 */
public interface BandcampClientConnection {

    public Reader getReader(String url) throws IOException;

}
