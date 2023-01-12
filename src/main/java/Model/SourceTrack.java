package Model;

public class SourceTrack implements Comparable {
    private Integer ID;
    private SourceType trackType;
    private String codec;
    private String language;

    public SourceTrack(Integer ID, SourceType trackType, String codec, String language) {
        this.ID = ID;
        this.trackType = trackType;
        this.codec = codec;
        this.language = language;
    }

    @Override
    public int compareTo(Object o) {
        SourceTrack oo = (SourceTrack) o;
        return this.ID - oo.ID;
    }

    @Override
    public String toString() {
        return ID +
                ":" + trackType +
                "(" + codec + '\'' +
                ")[" + language + ']';
    }
}
