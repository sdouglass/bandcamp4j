package org.bandcamp4j.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class javadoc comment here...
 *
 * @author sam
 * @version $Id$
 */
public class Discography {

    private List<Album> albums;

    private List<Track> tracks;

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
