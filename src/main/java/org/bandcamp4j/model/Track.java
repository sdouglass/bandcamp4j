package org.bandcamp4j.model;

public class Track {

    private long trackId;

    private long albumId;

    private long bandId;

    /**
     * The track's artist, if different from the band name.
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
     * 1 if the track is free, 2 if paid.
     */
    private int downloadable;

    private String lyrics;

    private String streamingUrl;

    private float duration;

    private int number;

    public long getTrackId() {
        return trackId;
    }

    public void setTrackId(long trackId) {
        this.trackId = trackId;
    }

    public long getBandId() {
        return bandId;
    }

    public void setBandId(long bandId) {
        this.bandId = bandId;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
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

    public String getStreamingUrl() {
        return streamingUrl;
    }

    public void setStreamingUrl(String streamingUrl) {
        this.streamingUrl = streamingUrl;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
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

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDownloadable() {
        return downloadable;
    }

    public void setDownloadable(int downloadable) {
        this.downloadable = downloadable;
    }

    public long getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(long releaseDate) {
        this.releaseDate = releaseDate;
    }
}
