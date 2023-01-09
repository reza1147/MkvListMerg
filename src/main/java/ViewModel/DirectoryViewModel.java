package ViewModel;

import Model.Directory;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DirectoryViewModel implements ViewModel {

    Directory directoryModel;

    StringProperty directoryName=new SimpleStringProperty("");

    public void initWithModel(Directory directoryModel) {
        this.directoryModel = directoryModel;
        directoryName.setValue(directoryModel.getDirectoryName());
    }

    public String getDirectoryName() {
        return directoryName.get();
    }

    public StringProperty directoryNameProperty() {
        return directoryName;
    }
}
