package Model;

public class SourceTrack implements Comparable {
    private Integer ID;
    private SourceType trackType;
    private String codec;
    private String language;
    private String name;

    public SourceTrack(Integer ID, SourceType trackType, String codec, String language, String name) {
        this.ID = ID;
        this.trackType = trackType;
        this.codec = codec;
        this.language = language;
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        SourceTrack oo = (SourceTrack) o;
        return this.ID - oo.ID;
    }

    public Integer getID() {
        return ID;
    }

    public SourceType getTrackType() {
        return trackType;
    }

    public String getCodec() {
        return codec;
    }

    public String getLanguage() {
        return language;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return ID + ":" + trackType +
                '(' + codec + ')' +
                '[' + language + ']' +
                '{' + name + '}';
    }
}
