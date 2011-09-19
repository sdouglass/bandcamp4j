package org.bandcamp4j;

/**
 * Class javadoc comment here...
 *
 * @author sam
 * @version $Id$
 */
public class BandcampException extends RuntimeException {

    public BandcampException() {
        super();
    }

    public BandcampException(String s) {
        super(s);
    }

    public BandcampException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public BandcampException(Throwable throwable) {
        super(throwable);
    }
}
