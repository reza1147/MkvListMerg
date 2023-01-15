package Service;

import Model.Directory;
import Model.SourceFile;
import javafx.concurrent.Task;

import java.io.File;
import java.util.Objects;

public class ReadDirectoryTask extends Task<Directory> {
    File directory;

    public ReadDirectoryTask(File directory) {
        this.directory = directory;
    }

    @Override
    protected Directory call() throws Exception {
        int size = Objects.requireNonNull(this.directory.listFiles()).length;
        Directory directory = new Directory(this.directory);
        updateMessage("Start reading " + this.directory.getAbsolutePath());
        for (int i = 0; i < size; i++) {
            updateTitle(String.format("%.0f%%",(i*100.0)/size));
            updateProgress(i, size);
            try {
                directory.addSourceFile(new SourceFile(Objects.requireNonNull(this.directory.listFiles())[i]));
                updateMessage("File " + Objects.requireNonNull(this.directory.listFiles())[i].getName() + " was readied");
            } catch (IllegalArgumentException | NullPointerException ignored) {
            }
        }
        updateProgress(size, size);
        updateTitle(String.format("%.0f%%",100.0));
        updateMessage("Reading " + this.directory.getAbsolutePath() + " Succeeded");
        return directory;
    }

}
