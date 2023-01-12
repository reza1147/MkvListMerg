package View;

import Model.SourceType;
import ViewModel.SourceFileViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SourceFileView implements FxmlView<SourceFileViewModel>, Initializable {
    @FXML
    private StackPane iconPane;
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
        videoIcon.visibleProperty().bind(viewModel.sourceFileTypeProperty().isEqualTo(SourceType.VIDEO));
        audioIcon.visibleProperty().bind(viewModel.sourceFileTypeProperty().isEqualTo(SourceType.AUDIO));
        subtitleIcon.visibleProperty().bind(viewModel.sourceFileTypeProperty().isEqualTo(SourceType.SUBTITLES));
    }

    public void showInfo(ActionEvent actionEvent) {
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
