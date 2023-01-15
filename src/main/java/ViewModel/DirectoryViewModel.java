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

public class DirectoryViewModel implements ViewModel {

    Directory directoryModel;
    StringProperty directoryName;
    ListProperty<SourceFile> videosList;
    ListProperty<SourceFile> audiosList;
    ListProperty<SourceFile> subtitlesList;

    public DirectoryViewModel() {
        directoryName = new SimpleStringProperty("");
        videosList = new SimpleListProperty<SourceFile>(FXCollections.observableArrayList());
        audiosList = new SimpleListProperty<SourceFile>(FXCollections.observableArrayList());
        subtitlesList = new SimpleListProperty<SourceFile>(FXCollections.observableArrayList());
    }

    public void initWithModel(Directory directoryModel) {
        this.directoryModel = directoryModel;
        directoryName.setValue(directoryModel.getDirectoryName());
        videosList.addAll(directoryModel.getVideos());
        audiosList.addAll(directoryModel.getAudios());
        subtitlesList.addAll(directoryModel.getSubtitles());
    }

    public String getDirectoryName() {
        return directoryName.get();
    }

    public StringProperty directoryNameProperty() {
        return directoryName;
    }

    public ObservableList<SourceFile> getVideosList() {
        return videosList.get();
    }

    public ListProperty<SourceFile> videosListProperty() {
        return videosList;
    }

    public ObservableList<SourceFile> getAudiosList() {
        return audiosList.get();
    }

    public ListProperty<SourceFile> audiosListProperty() {
        return audiosList;
    }

    public ObservableList<SourceFile> getSubtitlesList() {
        return subtitlesList.get();
    }

    public ListProperty<SourceFile> subtitlesListProperty() {
        return subtitlesList;
    }
}
