package Model;

import java.io.File;
import java.net.URI;

public class Directory{
    File directory;

    public Directory(File directory) {
        this.directory = directory;
        if (!this.directory.exists())
            throw new IllegalArgumentException("Directory is not exist!!");
        if(this.directory.isFile())
            throw new IllegalArgumentException("Path is not for Directory!!");
    }

    public Directory(String pathname) {
        this(new File(pathname));
    }

    public Directory(URI uri) {
        this(uri.getPath());
    }

    @Override
    public String toString() {
        return directory.getName();
    }
}
