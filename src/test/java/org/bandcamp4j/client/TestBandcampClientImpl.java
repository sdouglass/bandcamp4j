package org.bandcamp4j.client;

import org.apache.commons.io.IOUtils;
import org.bandcamp4j.model.*;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.*;

/**
 * Integration tests. Could break if artists change their Bandcamp info unfortunately.
 *
 */
public class TestBandcampClientImpl {

    // this is an invalid key, which is ok because the tests don't actually connect to the API
    private static final String API_KEY = "invalid-api-key";

    BandcampConnectionFileImpl connectionImpl = new BandcampConnectionFileImpl();

    BandcampClientImpl bandcampClient = new BandcampClientImpl(API_KEY);

    public TestBandcampClientImpl() {
        bandcampClient.setConnection(connectionImpl);
    }

    private static final String BAND_SEARCH_SINGLE_BAND_JSON =
        "{\"results\":[{\"subdomain\":\"jonathanmann\",\"band_id\":3288886718,\"name\":\"Jonathan Mann\"," +
        "\"url\":\"http:\\/\\/jonathanmann.bandcamp.com\",\"offsite_url\":\"http:\\/\\/www.rockcookiebottom.com\"}]}";

    @Test
    public void testBandSearch() throws IOException {

        try {
            bandcampClient.bandSearch();
            fail();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        connectionImpl.setResponseJson(BAND_SEARCH_SINGLE_BAND_JSON);

        List<Band> results = bandcampClient.bandSearch("jonathan mann");

        assertEquals(1, results.size());

        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("band_search_multiple.json");

        connectionImpl.setResponseJson(IOUtils.toString(stream));

        results = bandcampClient.bandSearch("jonathan mann", "cults");

        assertEquals(4, results.size());
    }

    @Test
    public void testDiscography() throws IOException {

        try {
            bandcampClient.bandDiscography();
            fail();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("band_discography_single.json");
        connectionImpl.setResponseJson(IOUtils.toString(stream));

        Map<Long, Discography> results = bandcampClient.bandDiscography(3288886718l);

        Discography discography = results.get(3288886718l);

        assertNotNull(discography);
        assertNotNull(discography.getAlbums());

        assertEquals(19, discography.getAlbums().size());

        stream = this.getClass().getClassLoader().getResourceAsStream("band_discography_multiple.json");
        connectionImpl.setResponseJson(IOUtils.toString(stream));

        results = bandcampClient.bandDiscography(3288886718l, 3463798201l, 203035041l);

        assertEquals(3, results.size());
    }

    private static final String BAND_INFO_SINGLE_JSON =
        "{\"subdomain\":\"jonathanmann\",\"band_id\":3288886718,\"name\":\"Jonathan Mann\"," +
        "\"url\":\"http:\\/\\/jonathanmann.bandcamp.com\",\"offsite_url\":\"http:\\/\\/www.rockcookiebottom.com\"}";

    @Test
    public void testBandInfo() throws IOException {

        try {
            bandcampClient.bandInfo();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        connectionImpl.setResponseJson(BAND_INFO_SINGLE_JSON);

        Map<Long, Band> results = bandcampClient.bandInfo(3288886718l);

        Band band = results.get(3288886718l);

        assertNotNull(band);
        assertTrue("Jonathan Mann".equals(band.getName()));

        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("band_info_multiple.json");
        connectionImpl.setResponseJson(IOUtils.toString(stream));

        results = bandcampClient.bandInfo(3463798201l, 203035041l);

        assertEquals(2, results.size());

        band = results.get(203035041l);

        assertNotNull(band);
        assertTrue("Sufjan Stevens".equals(band.getName()));

        band = results.get(3463798201l);

        assertNotNull(band);
        assertTrue("Amanda Palmer".equals(band.getName()));
    }

    @Test
    public void testAlbumInfo() throws IOException {
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("album_info.json");
        connectionImpl.setResponseJson(IOUtils.toString(stream));

        Album album = bandcampClient.albumInfo(88812510l);

        assertNotNull(album);
        assertNotNull(album.getTracks());
        assertTrue(!album.getTracks().isEmpty());
        assertEquals(372, album.getTracks().size());
    }

    @Test
    public void testTrackInfo() throws IOException {
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("track_info.json");
        connectionImpl.setResponseJson(IOUtils.toString(stream));

        Track track = bandcampClient.trackInfo(3748878242l);

        assertNotNull(track);
    }

    private static final String BAND_URL_INFO_JSON = "{\"band_id\":4214473200}";
    private static final String TRACK_URL_INFO_JSON = "{\"band_id\":3288886718,\"track_id\":4394253}";

    @Test
    public void testUrlInfo() {
        connectionImpl.setResponseJson(BAND_URL_INFO_JSON);

        UrlInfo urlInfo = bandcampClient.urlInfo("cults.bandcamp.com");

        assertNotNull(urlInfo);
        assertNotNull(urlInfo.getBandId());
        assertEquals(urlInfo.getBandId().longValue(), 4214473200l);

        connectionImpl.setResponseJson(TRACK_URL_INFO_JSON);

        urlInfo = bandcampClient.urlInfo("http://jonathanmann.bandcamp.com/track/dance-and-dance-again-alone-294");

        assertNotNull(urlInfo);
        assertNotNull(urlInfo.getBandId());
        assertEquals(urlInfo.getBandId().longValue(), 3288886718l);
        assertNotNull(urlInfo.getTrackId());
        assertEquals(urlInfo.getTrackId().longValue(), 4394253l);
    }
}
