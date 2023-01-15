package Model;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class Directory {
    private File directory;
    private List<SourceFile> videos;
    private List<SourceFile> audios;
    private List<SourceFile> subtitles;

    public Directory(File directory) throws IllegalArgumentException {
        this.directory = directory;
        if (!this.directory.exists())
            throw new IllegalArgumentException("Directory is not exist!!");
        if (this.directory.isFile())
            throw new IllegalArgumentException("Path is not for Directory!!");
        videos = new ArrayList<>();
        audios = new ArrayList<>();
        subtitles = new ArrayList<>();
    }

    public Directory(String pathname) throws IllegalArgumentException {
        this(new File(pathname));
    }

    public Directory(URI uri) throws IllegalArgumentException {
        this(uri.getPath());
    }

    public void addSourceFile(SourceFile file) {
        switch (file.getType()) {
            case VIDEO:
                videos.add(file);
                break;
            case AUDIO:
                audios.add(file);
                break;
            case SUBTITLES:
                subtitles.add(file);
                break;
        }
    }

    @Override
    public String toString() {
        return directory.getName();
    }

    public String getDirectoryName() {
        return directory.getName();
    }

    public List<SourceFile> getVideos() {
        return videos;
    }

    public List<SourceFile> getAudios() {
        return audios;
    }

    public List<SourceFile> getSubtitles() {
        return subtitles;
    }

}
