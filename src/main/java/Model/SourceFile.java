package Model;

import java.io.File;
import java.net.URI;

public class SourceFile {
    private File source;
    private SourceType type;

    public SourceFile(File sourceFile) throws IllegalArgumentException {
        this.source = sourceFile;
        if (!this.source.exists())
            throw new IllegalArgumentException("Source is not exist!!");
        if(!this.source.isFile())
            throw new IllegalArgumentException("Path is not for Valid File!!");
        type=SourceTypeFinder.findType(source);
    }

    public SourceFile(String pathname) throws IllegalArgumentException {
        this(new File(pathname));
    }

    public SourceFile(URI uri) throws IllegalArgumentException {
        this(uri.getPath());
    }

    @Override
    public String toString() {
        return source.getName();
    }

    public String getSourceFileName(){
        return source.getName();
    }

    public SourceType getType() {
        return type;
    }
}
