package ViewModel;

import Model.SourceTrack;
import Model.SourceType;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.*;

public class SourceTrackViewModel implements ViewModel {
    private SourceTrack model;
    private IntegerProperty ID;
    private ObjectProperty<SourceType> trackType;
    private StringProperty codec;
    private StringProperty language;
    private StringProperty name;

    public SourceTrackViewModel() {
        ID = new SimpleIntegerProperty();
        codec = new SimpleStringProperty("");
        language = new SimpleStringProperty("");
        name = new SimpleStringProperty("");
        trackType = new SimpleObjectProperty<>();
    }

    public void initWithModel(SourceTrack sourceTrackModel) {
        this.model = sourceTrackModel;
        ID.setValue(model.getID());
        trackType.setValue(model.getTrackType());
        codec.setValue(model.getCodec());
        language.setValue(model.getLanguage());
        name.setValue(model.getName());
    }

    public SourceTrack getModel() {
        return model;
    }

    public int getID() {
        return ID.get();
    }

    public IntegerProperty IDProperty() {
        return ID;
    }

    public SourceType getTrackType() {
        return trackType.get();
    }

    public ObjectProperty<SourceType> trackTypeProperty() {
        return trackType;
    }

    public String getCodec() {
        return codec.get();
    }

    public StringProperty codecProperty() {
        return codec;
    }

    public String getLanguage() {
        return language.get();
    }

    public StringProperty languageProperty() {
        return language;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }
}
