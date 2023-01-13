package Model;

import Util.Mkvinfo;
import Util.Mkvmerg;

import java.io.File;
import java.net.URI;
import java.util.Set;
import java.util.TreeSet;

public class SourceFile {
    private File source;
    private SourceType type;
    private Set<SourceTrack> tracks;

    public SourceFile(File sourceFile) throws IllegalArgumentException {
        this.source = sourceFile;
        if (!this.source.exists())
            throw new IllegalArgumentException("Source is not exist!!");
        if (!this.source.isFile())
            throw new IllegalArgumentException("Path is not for Valid File!!");
        type = SourceTypeFinder.findType(source);
        tracks = new TreeSet<SourceTrack>(Mkvmerg.readTracks(sourceFile));
    }

    public SourceFile(String pathname) throws IllegalArgumentException {
        this(new File(pathname));
    }

    public SourceFile(URI uri) throws IllegalArgumentException {
        this(uri.getPath());
    }

    @Override
    public String toString() {
        final String[] s = {source.getName() + ":\n"};
        tracks.forEach(t -> s[0] += t + "\n");
        return s[0];
    }

    public String getSourceFileName() {
        return source.getName();
    }

    public SourceType getType() {
        return type;
    }

    public File getSource() {
        return source;
    }

    public Set<SourceTrack> getTracks() {
        return tracks;
    }

    public String information() {
        return Mkvinfo.readInfo(source);
    }
}
