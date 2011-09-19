package org.bandcamp4j.model;

/**
 * Class javadoc comment here...
 *
 * @author sam
 * @version $Id$
 */
public class Band {

    private long bandId;

    private String subdomain;

    private String name;

    private String url;

    private String offsiteUrl;

    public long getBandId() {
        return bandId;
    }

    public void setBandId(long bandId) {
        this.bandId = bandId;
    }

    public String getSubdomain() {
        return subdomain;
    }

    public void setSubdomain(String subdomain) {
        this.subdomain = subdomain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOffsiteUrl() {
        return offsiteUrl;
    }

    public void setOffsiteUrl(String offsiteUrl) {
        this.offsiteUrl = offsiteUrl;
    }
}
