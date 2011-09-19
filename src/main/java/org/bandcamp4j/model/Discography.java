package org.bandcamp4j.model;

import java.util.List;

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
