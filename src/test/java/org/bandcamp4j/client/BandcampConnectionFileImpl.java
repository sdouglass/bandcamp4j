package org.bandcamp4j.client;

import java.io.*;

/**
 * Class javadoc comment here...
 *
 * @author sam
 * @version $Id$
 */
public class BandcampConnectionFileImpl implements BandcampClientConnection {

    Reader reader;

    public BandcampConnectionFileImpl() {

    }

    public void setResponseJson(String responseJson) {
        reader = new StringReader(responseJson);
    }

    public void setResponseJson(InputStream responseJson) {
        reader = new InputStreamReader(responseJson);
    }

    public Reader getReader(String url) throws IOException {
        return reader;
    }
}
