package View;

import Model.Directory;
import ViewModel.MainViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainView implements FxmlView<MainViewModel>, Initializable {
    @FXML
    private HBox centerPane;
    @FXML
    private BorderPane mainPane;

    @InjectViewModel
    private MainViewModel viewModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewModel.directoriesListProperty().addListener(new ListChangeListener<Directory>() {
            @Override
            public void onChanged(Change<? extends Directory> c) {
                if (c.next())
                    c.getAddedSubList().forEach(addedDirectory -> {
                        Label lbl = new Label();
                        lbl.setId(String.valueOf(viewModel.directoriesListProperty().indexOf(addedDirectory)));
                        lbl.setText(String.valueOf(addedDirectory));
                        centerPane.getChildren().add(lbl);
                    });
            }
        });
    }

    public void addFolder(ActionEvent actionEvent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(mainPane.getScene().getWindow());
        viewModel.addDirectory(selectedDirectory);
    }
}
