package View;

import ViewModel.DirectoryViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DirectoryView implements FxmlView<DirectoryViewModel>, Initializable {
    @FXML
    private Label name;


    @InjectViewModel
    DirectoryViewModel directoryViewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.textProperty().bind(directoryViewModel.directoryNameProperty());
    }
}
