package View;

import Model.Directory;
import Service.MakeDirectoryViewTask;
import ViewModel.MainViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class MainView implements FxmlView<MainViewModel>, Initializable {
    @FXML
    private Label percent;
    @FXML
    private ProgressBar progress;
    @FXML
    private Label message;
    @FXML
    private HBox centerPane;
    @FXML
    private BorderPane mainPane;

    @InjectViewModel
    private MainViewModel viewModel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        progress.progressProperty().bind(viewModel.progressProperty());
        message.textProperty().bind(viewModel.messageProperty());
        percent.textProperty().bind(viewModel.percentProperty());
        viewModel.directoriesListProperty().addListener(new ListChangeListener<Directory>() {
            @Override
            public void onChanged(Change<? extends Directory> c) {
                if (c.next())
                    c.getAddedSubList().forEach(addedDirectory -> {
                        MakeDirectoryViewTask makeDirectoryViewTask = new MakeDirectoryViewTask(addedDirectory);
                        viewModel.progressProperty().bind(makeDirectoryViewTask.progressProperty());
                        viewModel.messageProperty().bind(makeDirectoryViewTask.messageProperty());
                        viewModel.percentProperty().bind(makeDirectoryViewTask.titleProperty());
                        makeDirectoryViewTask.setOnSucceeded(event -> {
                            try {
                                viewModel.progressProperty().unbind();
                                viewModel.messageProperty().unbind();
                                viewModel.percentProperty().unbind();
                                centerPane.getChildren().add(makeDirectoryViewTask.get().getView());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }
                        });
                        new Thread(makeDirectoryViewTask).start();
                    });
            }
        });
    }

    public void addFolder(ActionEvent actionEvent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(mainPane.getScene().getWindow());
        if (selectedDirectory != null)
            viewModel.addDirectory(selectedDirectory);
    }
}
