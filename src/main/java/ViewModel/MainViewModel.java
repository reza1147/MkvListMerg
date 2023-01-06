package ViewModel;

import Model.Directory;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.ArrayList;

public class MainViewModel implements ViewModel {
    ListProperty<Directory> directoriesList;

    public MainViewModel() {
        directoriesList = new SimpleListProperty<Directory>(FXCollections.observableList(new ArrayList<Directory>()));
    }

    public ObservableList<Directory> getDirectoriesList() {
        return directoriesList.get();
    }

    public ListProperty<Directory> directoriesListProperty() {
        return directoriesList;
    }

    public void addDirectory(File directory) {
        directoriesList.add(new Directory(directory));
    }

    public void addDirectory(String directoryPath) {
        directoriesList.add(new Directory(directoryPath));
    }
}
