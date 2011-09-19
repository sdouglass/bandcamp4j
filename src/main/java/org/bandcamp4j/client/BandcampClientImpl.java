package org.bandcamp4j.client;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.bandcamp4j.BandcampException;
import org.bandcamp4j.model.Album;
import org.bandcamp4j.model.Band;
import org.bandcamp4j.model.Discography;
import org.bandcamp4j.model.Track;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Class javadoc comment here...
 *
 * @author sam
 * @version $Id$
 */
public class BandcampClientImpl implements BandcampClient {

    private Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

    private String apiKey;

    private boolean debug;

    public BandcampClientImpl(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public List<Band> bandSearch(String... names) {
        if (names.length == 0) {
            throw new IllegalArgumentException("Please provide at least one band name");
        }
        try {
            // base url
            String baseUrl = "http://api.bandcamp.com/api/band/3/search?key={0}&name={1}";
            // build params
            String nameParams = toUrlEncodedCommaSeparatedList(names);
            // substitute in params to get final url
            String finalUrl = MessageFormat.format(baseUrl, apiKey, nameParams);
            URL bandSearchUrl = new URL(finalUrl);
            // make request
            InputStream inputStream = bandSearchUrl.openStream();
            Reader reader = new BufferedReader(new InputStreamReader(inputStream));

            Type type = new TypeToken<Map<String, List<Band>>>() {}.getType();

            Map<String, List<Band>> result = gson.fromJson(reader, type);

            return result.get("results");
        } catch (Exception e) {
            throw new BandcampException(e);
        }
    }

    public Map<Long, Discography> bandDiscography(Long... bandIds) {
        if (bandIds.length == 0) {
            throw new IllegalArgumentException("Please provide at least one band id");
        }
        try {
            // base url
            String baseUrl = "http://api.bandcamp.com/api/band/3/discography?key={0}&band_id={1}";
            // build params
            String nameParams = BandcampClientImpl.toUrlEncodedCommaSeparatedList(bandIds);
            // substitute in params to get final url
            String finalUrl = MessageFormat.format(baseUrl, apiKey, nameParams);
            URL bandSearchUrl = new URL(finalUrl);
            // make request
            InputStream inputStream = bandSearchUrl.openStream();
            Reader reader = new BufferedReader(new InputStreamReader(inputStream));

            JsonParser parser = new JsonParser();

            JsonObject jsonObject = parser.parse(reader).getAsJsonObject();

            Map<Long, Discography> results = new LinkedHashMap<Long, Discography>();

            JsonElement discographyElement = jsonObject.get("discography");
            if (discographyElement != null) {
                // single discography response
                Discography discography = deserializeDiscography(discographyElement);
                results.put(bandIds[0], discography);
            } else {
                // multiple discography response
                for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                    String bandIdString = entry.getKey();
                    Long bandId = Long.valueOf(bandIdString);
                    discographyElement = entry.getValue().getAsJsonObject().get("discography");
                    Discography discography = deserializeDiscography(discographyElement);
                    results.put(bandId, discography);
                }
            }

            return results;
        } catch (Exception e) {
            throw new BandcampException(e);
        }
    }

    public Discography deserializeDiscography(JsonElement element) {
        JsonArray array = element.getAsJsonArray();

        Discography discography = new Discography();
        List<Album> albums = new ArrayList<Album>();
        List<Track> tracks = new ArrayList<Track>();

        for (int i = 0; i < array.size(); i++) {
            JsonElement arrayItem = array.get(i);
            if (arrayItem.getAsJsonObject().get("track_id") != null) {
                Track track = gson.fromJson(arrayItem, Track.class);
                tracks.add(track);
            } else {
                Album album = gson.fromJson(arrayItem, Album.class);
                albums.add(album);
            }
        }

        discography.setAlbums(albums);
        discography.setTracks(tracks);

        return discography;
    }

    public Map<Long, Band> bandInfo(Long... bandIds) {
        if (bandIds.length == 0) {
            throw new IllegalArgumentException("Please provide at least one band id");
        }
        try {
            // base url
            String baseUrl = "http://api.bandcamp.com/api/band/3/info?key={0}&band_id={1}";
            // build params
            String nameParams = BandcampClientImpl.toUrlEncodedCommaSeparatedList(bandIds);
            // substitute in params to get final url
            String finalUrl = MessageFormat.format(baseUrl, apiKey, nameParams);
            URL bandSearchUrl = new URL(finalUrl);
            // make request
            InputStream inputStream = bandSearchUrl.openStream();
            Reader reader = new BufferedReader(new InputStreamReader(inputStream));

            JsonParser parser = new JsonParser();

            JsonObject jsonObject = parser.parse(reader).getAsJsonObject();

            Map<Long, Band> results = new LinkedHashMap<Long, Band>();

            if (jsonObject.get("band_id") != null) {
                // single band response
                Band band = gson.fromJson(jsonObject, Band.class);
                results.put(bandIds[0], band);
            } else {
                // multiple band response
                for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                    String bandIdString = entry.getKey();
                    Long bandId = Long.valueOf(bandIdString);
                    Band band = gson.fromJson(entry.getValue(), Band.class);
                    results.put(bandId, band);
                }
            }

            return results;
        } catch (Exception e) {
            throw new BandcampException(e);
        }
    }

    public Album albumInfo(long albumId) {
        try {
            // base url
            String baseUrl = "http://api.bandcamp.com/api/album/2/info?key={0}&album_id={1}";
            // substitute in params to get final url
            String finalUrl = MessageFormat.format(baseUrl, apiKey, Long.toString(albumId));
            URL bandSearchUrl = new URL(finalUrl);
            // make request
            InputStream inputStream = bandSearchUrl.openStream();
            Reader reader = new BufferedReader(new InputStreamReader(inputStream));

            return gson.fromJson(reader, Album.class);
        } catch (Exception e) {
            throw new BandcampException(e);
        }
    }

    public Track trackInfo(long trackId) {
        try {
            // base url
            String baseUrl = "http://api.bandcamp.com/api/track/1/info?key={0}&track_id={1}";
            // substitute in params to get final url
            String finalUrl = MessageFormat.format(baseUrl, apiKey, Long.toString(trackId));
            URL bandSearchUrl = new URL(finalUrl);
            // make request
            InputStream inputStream = bandSearchUrl.openStream();
            Reader reader = new BufferedReader(new InputStreamReader(inputStream));

            return gson.fromJson(reader, Track.class);
        } catch (Exception e) {
            throw new BandcampException(e);
        }
    }

    public long urlInfo(String url) {
        try {
            // base url
            String baseUrl = "http://api.bandcamp.com/api/url/1/info?key={0}&url={1}";
            // substitute in params to get final url
            String finalUrl = MessageFormat.format(baseUrl, apiKey, URLEncoder.encode(url, "UTF-8"));
            URL bandSearchUrl = new URL(finalUrl);
            // make request
            InputStream inputStream = bandSearchUrl.openStream();
            Reader reader = new BufferedReader(new InputStreamReader(inputStream));

            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(reader).getAsJsonObject();

            return jsonObject.entrySet().iterator().next().getValue().getAsLong();
        } catch (Exception e) {
            throw new BandcampException(e);
        }
    }

    public static String toUrlEncodedCommaSeparatedList(Object... objects) throws UnsupportedEncodingException {
        StringBuilder params = new StringBuilder();
        for (Object object : objects) {
            params.append(URLEncoder.encode(object.toString(), "UTF-8"));
            params.append(',');
        }
        params.substring(0, params.length() - 1);
        return params.toString();
    }
}
