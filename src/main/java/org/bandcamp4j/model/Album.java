package org.bandcamp4j.model;

import java.util.List;

public class Album {

    private long albumId;
    
    private long bandId;

    /**
     * The album's artist, if different from the band name.
     */
    private String artist;

    private String title;

    private String url;

    private String about;

    private String credits;

    private String smallArtUrl;
    
    private String largeArtUrl;

    private long releaseDate;

    /**
     * 1 if the album is free, 2 if paid.
     */
    private int downloadable;

    private List<Track> tracks;

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public long getBandId() {
        return bandId;
    }

    public void setBandId(long bandId) {
        this.bandId = bandId;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getSmallArtUrl() {
        return smallArtUrl;
    }

    public void setSmallArtUrl(String smallArtUrl) {
        this.smallArtUrl = smallArtUrl;
    }

    public String getLargeArtUrl() {
        return largeArtUrl;
    }

    public void setLargeArtUrl(String largeArtUrl) {
        this.largeArtUrl = largeArtUrl;
    }

    public long getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(long releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getDownloadable() {
        return downloadable;
    }

    public void setDownloadable(int downloadable) {
        this.downloadable = downloadable;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
