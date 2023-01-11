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
    private ObjectProperty<SourceType> sourceFileType;

    public SourceFileViewModel() {
        sourceFileName = new SimpleStringProperty("");
        sourceFileType = new SimpleObjectProperty<>();
    }

    public void initWithModel(SourceFile sourceFileModel) {
        this.sourceFileModel = sourceFileModel;
        sourceFileName.setValue(sourceFileModel.getSourceFileName());
        sourceFileType.setValue(sourceFileModel.getType());
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
}
