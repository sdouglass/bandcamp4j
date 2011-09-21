package org.bandcamp4j.client;

import org.bandcamp4j.model.*;

import java.util.List;
import java.util.Map;

public interface BandcampClient {

    List<Band> bandSearch(String... names);

    Map<Long, Discography> bandDiscography(Long... bandIds);

    Map<Long, Band> bandInfo(Long... bandIds);

    Album albumInfo(long albumId);

    Track trackInfo(long trackId);

    UrlInfo urlInfo(String url);

}
