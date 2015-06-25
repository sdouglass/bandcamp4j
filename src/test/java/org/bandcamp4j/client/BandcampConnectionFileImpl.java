package org.bandcamp4j.client;

import java.io.*;

/**
 * Class javadoc comment here...
 *
 * @author sam
 * @version $Id$
 */
public class BandcampConnectionFileImpl implements BandcampClientConnection {

    String responseJson;

    public BandcampConnectionFileImpl() {

    }

    public void setResponseJson(String responseJson) {
        this.responseJson = responseJson;
    }

    public String getJson(String url) throws IOException {
        return responseJson;
    }
}
