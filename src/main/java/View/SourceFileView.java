package View;

import Model.SourceTrack;
import Model.SourceType;
import ViewModel.SourceFileViewModel;
import ViewModel.SourceTrackViewModel;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.collections.ListChangeListener;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class SourceFileView implements FxmlView<SourceFileViewModel>, Initializable {
    @FXML
    private HBox tracks;
    @FXML
    private Tooltip fileNameTooltip;
    @FXML
    private Label fileName;
    @FXML
    private ImageView videoIcon;
    @FXML
    private ImageView audioIcon;
    @FXML
    private ImageView subtitleIcon;
    @InjectViewModel
    private SourceFileViewModel viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileName.textProperty().bind(viewModel.sourceFileNameProperty());
        fileNameTooltip.textProperty().bind(viewModel.sourceFileNameProperty());
        videoIcon.visibleProperty().bind(viewModel.sourceFileTypeProperty().isEqualTo(SourceType.VIDEO));
        audioIcon.visibleProperty().bind(viewModel.sourceFileTypeProperty().isEqualTo(SourceType.AUDIO));
        subtitleIcon.visibleProperty().bind(viewModel.sourceFileTypeProperty().isEqualTo(SourceType.SUBTITLES));
        viewModel.getTracks().addListener(new ListChangeListener<SourceTrack>() {
            @Override
            public void onChanged(Change<? extends SourceTrack> c) {
                if (c.next())
                    c.getAddedSubList().forEach(track -> {
                        ViewTuple<SourceTrackView, SourceTrackViewModel> viewTuple = FluentViewLoader.fxmlView(SourceTrackView.class).load();
                        viewTuple.getViewModel().initWithModel(track);
                        tracks.getChildren().add(viewTuple.getView());
                    });
            }
        });
    }


    public void showInfo(Event actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setContentText("Information of the file:");
        TextArea textArea = new TextArea(viewModel.informationsProperty().get());
        textArea.setEditable(false);
        textArea.setWrapText(true);
        alert.getDialogPane().setExpandableContent(textArea);
        alert.getDialogPane().expandedProperty().setValue(true);
        alert.showAndWait();
    }
}
