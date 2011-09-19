package org.bandcamp4j.client;

import org.bandcamp4j.model.Album;
import org.bandcamp4j.model.Band;
import org.bandcamp4j.model.Discography;
import org.bandcamp4j.model.Track;
import org.junit.Test;

import java.io.*;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.*;

/**
 * Class javadoc comment here...
 *
 * @author sam
 * @version $Id$
 */
public class TestBandcampClientImpl {

    private static final String API_KEY = "snaefellsjokull";

    @Test
    public void testBandSearch() {
        BandcampClientImpl bandcampClient = new BandcampClientImpl(API_KEY);

        try {
            bandcampClient.bandSearch();
            fail();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        List<Band> results = bandcampClient.bandSearch("jonathan mann");

        assertEquals(1, results.size());

        results = bandcampClient.bandSearch("jonathan mann", "cults");

        assertEquals(4, results.size());
    }

    @Test
    public void testDiscography() throws IOException, MalformedURLException {
        BandcampClientImpl bandcampClient = new BandcampClientImpl(API_KEY);

        try {
            bandcampClient.bandDiscography();
            fail();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        Map<Long, Discography> results = bandcampClient.bandDiscography(3288886718l);

        Discography discography = results.get(3288886718l);

        assertNotNull(discography);
        assertNotNull(discography.getAlbums());

        assertEquals(19, discography.getAlbums().size());

        results = bandcampClient.bandDiscography(3288886718l, 3463798201l, 203035041l);

        assertEquals(3, results.size());
    }

    @Test
    public void testBandInfo() {
        BandcampClientImpl bandcampClient = new BandcampClientImpl(API_KEY);

        try {
            bandcampClient.bandInfo();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        Map<Long, Band> results = bandcampClient.bandInfo(3288886718l);

        Band band = results.get(3288886718l);

        assertNotNull(band);
        assertTrue("Jonathan Mann".equals(band.getName()));

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
    public void testAlbumInfo() {
        BandcampClientImpl bandcampClient = new BandcampClientImpl(API_KEY);

        Album album = bandcampClient.albumInfo(88812510l);

        assertNotNull(album);
        assertNotNull(album.getTracks());
        assertTrue(!album.getTracks().isEmpty());
    }

    @Test
    public void testTrackInfo() {
        BandcampClientImpl bandcampClient = new BandcampClientImpl(API_KEY);

        Track track = bandcampClient.trackInfo(3748878242l);

        assertNotNull(track);
    }

    @Test
    public void testUrlInfo() {
        BandcampClientImpl bandcampClient = new BandcampClientImpl(API_KEY);

        long id = bandcampClient.urlInfo("cults.bandcamp.com");

        assertEquals(id, 4214473200l);
    }
}
