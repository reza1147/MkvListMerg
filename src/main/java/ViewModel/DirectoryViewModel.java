package ViewModel;

import Model.Directory;
import Model.SourceFile;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class DirectoryViewModel implements ViewModel {

    Directory directoryModel;
    StringProperty directoryName;
    ListProperty<SourceFile> sourceFileViewModels;

    public DirectoryViewModel() {
        directoryName = new SimpleStringProperty("");
        sourceFileViewModels = new SimpleListProperty<SourceFile>(FXCollections.observableArrayList());
    }

    public void initWithModel(Directory directoryModel) {
        this.directoryModel = directoryModel;
        directoryName.setValue(directoryModel.getDirectoryName());
        sourceFileViewModels.addAll(directoryModel.getFiles());
    }

    public String getDirectoryName() {
        return directoryName.get();
    }

    public StringProperty directoryNameProperty() {
        return directoryName;
    }

    public ObservableList<SourceFile> getSourceFileViewModels() {
        return sourceFileViewModels.get();
    }

    public ListProperty<SourceFile> sourceFileViewModelsProperty() {
        return sourceFileViewModels;
    }
}
