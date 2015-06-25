package org.bandcamp4j.model;

public class UrlInfo {

  private Long bandId;
  private Long albumId;
  private Long trackId;

  public Long getBandId() {
    return bandId;
  }

  public void setBandId(Long bandId) {
    this.bandId = bandId;
  }

  public Long getAlbumId() {
    return albumId;
  }

  public void setAlbumId(Long albumId) {
    this.albumId = albumId;
  }

  public Long getTrackId() {
    return trackId;
  }

  public void setTrackId(Long trackId) {
    this.trackId = trackId;
  }
}
