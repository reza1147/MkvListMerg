package View;

import Model.SourceType;
import ViewModel.SourceTrackViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SourceTrackView implements Initializable, FxmlView<SourceTrackViewModel> {

    @FXML
    private ImageView videoIcon;
    @FXML
    private ImageView audioIcon;
    @FXML
    private ImageView subtitleIcon;
    @FXML
    private StackPane iconPane;
    @InjectViewModel
    private SourceTrackViewModel viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        videoIcon.visibleProperty().bind(viewModel.trackTypeProperty().isEqualTo(SourceType.VIDEO));
        audioIcon.visibleProperty().bind(viewModel.trackTypeProperty().isEqualTo(SourceType.AUDIO));
        subtitleIcon.visibleProperty().bind(viewModel.trackTypeProperty().isEqualTo(SourceType.SUBTITLES));
        Tooltip tip = new Tooltip();
        tip.textProperty().bind(viewModel.IDProperty().asString()
                .concat(":")
                .concat(viewModel.languageProperty())
                .concat("\nCodec: ")
                .concat(viewModel.codecProperty())
                .concat("\nTitle: ")
                .concat(viewModel.nameProperty()));
        Tooltip.install(iconPane, tip);
    }
}
