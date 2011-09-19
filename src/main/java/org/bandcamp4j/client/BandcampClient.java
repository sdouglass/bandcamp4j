package org.bandcamp4j.client;

import org.bandcamp4j.model.Album;
import org.bandcamp4j.model.Band;
import org.bandcamp4j.model.Discography;
import org.bandcamp4j.model.Track;

import java.util.List;
import java.util.Map;

/**
 * Class javadoc comment here...
 *
 * @author sam
 * @version $Id$
 */
public interface BandcampClient {

    List<Band> bandSearch(String... names);

    Map<Long, Discography> bandDiscography(Long... bandIds);

    Map<Long, Band> bandInfo(Long... bandIds);

    Album albumInfo(long albumId);

    Track trackInfo(long trackId);

    long urlInfo(String url);

}
