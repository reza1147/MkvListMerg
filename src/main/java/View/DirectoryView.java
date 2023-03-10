package View;

import Model.SourceFile;
import ViewModel.DirectoryViewModel;
import ViewModel.SourceFileViewModel;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.ResourceBundle;

public class DirectoryView implements FxmlView<DirectoryViewModel>, Initializable {
    @FXML
    private BorderPane directoryPane;
    @FXML
    private TilePane videoTiles;
    @FXML
    private TilePane audioTiles;
    @FXML
    private TilePane subtitleTiles;
    @FXML
    private Tooltip nameTooltip;
    @FXML
    private Label name;

    IntegerProperty columns;
    BooleanProperty hasVideo;
    BooleanProperty hasSubTitle;
    BooleanProperty hasAudio;

    @InjectViewModel
    DirectoryViewModel viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columns = new SimpleIntegerProperty(0);
        hasVideo = new SimpleBooleanProperty(false);
        hasSubTitle = new SimpleBooleanProperty(false);
        hasAudio = new SimpleBooleanProperty(false);
        name.textProperty().bind(viewModel.directoryNameProperty());
        nameTooltip.textProperty().bind(viewModel.directoryNameProperty());
        ChangeListener<Boolean> changeColumns = (observable, oldValue, newValue) -> {
            if (newValue) {
                columns.setValue(columns.getValue() + 1);
            } else {
                columns.setValue(columns.getValue() - 1);
            }
        };
        directoryPane.prefWidthProperty().bind(columns.multiply(400));
        directoryPane.minWidthProperty().bind(directoryPane.prefWidthProperty());
        directoryPane.maxWidthProperty().bind(directoryPane.prefWidthProperty());
        hasVideo.addListener(changeColumns);
        hasAudio.addListener(changeColumns);
        hasSubTitle.addListener(changeColumns);
        viewModel.videosListProperty().addListener(new ListChangeListener<SourceFile>() {
            @Override
            public void onChanged(Change<? extends SourceFile> c) {
                if (c.next())
                    c.getAddedSubList().forEach(sourceFile -> {
                        ViewTuple<SourceFileView, SourceFileViewModel> viewTuple = FluentViewLoader.fxmlView(SourceFileView.class).load();
                        viewTuple.getViewModel().initWithModel(sourceFile);
                        videoTiles.getChildren().add(viewTuple.getView());
                        hasVideo.setValue(true);
                    });
            }
        });
        viewModel.audiosListProperty().addListener(new ListChangeListener<SourceFile>() {
            @Override
            public void onChanged(Change<? extends SourceFile> c) {
                if (c.next())
                    c.getAddedSubList().forEach(sourceFile -> {
                        ViewTuple<SourceFileView, SourceFileViewModel> viewTuple = FluentViewLoader.fxmlView(SourceFileView.class).load();
                        viewTuple.getViewModel().initWithModel(sourceFile);
                        audioTiles.getChildren().add(viewTuple.getView());
                        hasAudio.setValue(true);
                    });
            }
        });
        viewModel.subtitlesListProperty().addListener(new ListChangeListener<SourceFile>() {
            @Override
            public void onChanged(Change<? extends SourceFile> c) {
                if (c.next())
                    c.getAddedSubList().forEach(sourceFile -> {
                        ViewTuple<SourceFileView, SourceFileViewModel> viewTuple = FluentViewLoader.fxmlView(SourceFileView.class).load();
                        viewTuple.getViewModel().initWithModel(sourceFile);
                        subtitleTiles.getChildren().add(viewTuple.getView());
                        hasSubTitle.setValue(true);
                    });
            }
        });
    }
}
