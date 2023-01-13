package ViewModel;

import Model.SourceFile;
import Model.SourceTrack;
import Model.SourceType;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SourceFileViewModel implements ViewModel {

    private SourceFile sourceFileModel;
    private StringProperty sourceFileName;
    private StringProperty informations;
    private ObjectProperty<SourceType> sourceFileType;
    private ListProperty<SourceTrack> tracks;

    public SourceFileViewModel() {
        sourceFileName = new SimpleStringProperty("");
        sourceFileType = new SimpleObjectProperty<>();
        informations = new SimpleStringProperty();
        tracks = new SimpleListProperty<>(FXCollections.observableArrayList());
    }

    public void initWithModel(SourceFile sourceFileModel) {
        this.sourceFileModel = sourceFileModel;
        sourceFileName.setValue(sourceFileModel.getSourceFileName());
        sourceFileType.setValue(sourceFileModel.getType());
        informations.setValue(sourceFileModel.information());
        tracks.addAll(sourceFileModel.getTracks());
    }

    public SourceType getSourceFileType() {
        return sourceFileType.get();
    }

    public ObjectProperty<SourceType> sourceFileTypeProperty() {
        return sourceFileType;
    }

    public String getSourceFileName() {
        return sourceFileName.get();
    }

    public StringProperty sourceFileNameProperty() {
        return sourceFileName;
    }

    public String getInformations() {
        return informations.get();
    }

    public StringProperty informationsProperty() {
        return informations;
    }

    public ObservableList<SourceTrack> getTracks() {
        return tracks.get();
    }

    public ListProperty<SourceTrack> tracksProperty() {
        return tracks;
    }
}
