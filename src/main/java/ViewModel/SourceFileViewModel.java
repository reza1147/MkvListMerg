package ViewModel;

import Model.SourceFile;
import Model.SourceType;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SourceFileViewModel implements ViewModel {

    private SourceFile sourceFileModel;
    private StringProperty sourceFileName;
    private StringProperty informations;
    private ObjectProperty<SourceType> sourceFileType;

    public SourceFileViewModel() {
        sourceFileName = new SimpleStringProperty("");
        sourceFileType = new SimpleObjectProperty<>();
        informations = new SimpleStringProperty();
    }

    public void initWithModel(SourceFile sourceFileModel) {
        this.sourceFileModel = sourceFileModel;
        sourceFileName.setValue(sourceFileModel.getSourceFileName());
        sourceFileType.setValue(sourceFileModel.getType());
        informations.setValue(sourceFileModel.information());
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
}