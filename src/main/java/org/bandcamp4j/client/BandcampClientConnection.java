package org.bandcamp4j.client;

import java.io.IOException;

/**
 * Handles connecting to Bandcamp.
 */
public interface BandcampClientConnection {

    String getJson(String url) throws IOException;

}
