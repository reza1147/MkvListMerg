package ViewModel;

import Model.Directory;
import Service.ReadDirectoryTask;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainViewModel implements ViewModel {
    ListProperty<Directory> directoriesList;
    DoubleProperty progress;
    StringProperty message;
    StringProperty percent;

    public MainViewModel() {
        directoriesList = new SimpleListProperty<Directory>(FXCollections.observableList(new ArrayList<Directory>()));
        progress = new SimpleDoubleProperty(0);
        message = new SimpleStringProperty("");
        percent = new SimpleStringProperty("0%");
    }

    public ObservableList<Directory> getDirectoriesList() {
        return directoriesList.get();
    }

    public ListProperty<Directory> directoriesListProperty() {
        return directoriesList;
    }

    public double getProgress() {
        return progress.get();
    }

    public DoubleProperty progressProperty() {
        return progress;
    }

    public String getMessage() {
        return message.get();
    }

    public StringProperty messageProperty() {
        return message;
    }

    public String getPercent() {
        return percent.get();
    }

    public StringProperty percentProperty() {
        return percent;
    }

    public void addDirectory(File directory) {
        ReadDirectoryTask readDirectoryTask = new ReadDirectoryTask(directory);
        progress.bind(readDirectoryTask.progressProperty());
        message.bind(readDirectoryTask.messageProperty());
        percent.bind(readDirectoryTask.titleProperty());
        readDirectoryTask.setOnSucceeded(e -> {
            try {
                directoriesList.add(readDirectoryTask.get());
                percent.unbind();
                progress.unbind();
                message.unbind();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            } catch (ExecutionException executionException) {
                executionException.printStackTrace();
            }
        });
        new Thread(readDirectoryTask).start();
    }

    public void addDirectory(String directoryPath) {
        directoriesList.add(new Directory(directoryPath));
    }
}
