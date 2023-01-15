package Model;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class Directory {
    private File directory;
    private List<SourceFile> files;

    public Directory(File directory) throws IllegalArgumentException {
        this.directory = directory;
        if (!this.directory.exists())
            throw new IllegalArgumentException("Directory is not exist!!");
        if (this.directory.isFile())
            throw new IllegalArgumentException("Path is not for Directory!!");
        files = new ArrayList<>();
    }

    public Directory(String pathname) throws IllegalArgumentException {
        this(new File(pathname));
    }

    public Directory(URI uri) throws IllegalArgumentException {
        this(uri.getPath());
    }

    public void addSourceFile(SourceFile file) {
        files.add(file);
    }

    @Override
    public String toString() {
        return directory.getName();
    }

    public String getDirectoryName() {
        return directory.getName();
    }

    public List<SourceFile> getFiles() {
        return files;
    }
}
