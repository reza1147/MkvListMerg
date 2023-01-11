package View;

import Model.Directory;
import Model.SourceFile;
import ViewModel.DirectoryViewModel;
import ViewModel.SourceFileViewModel;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.ResourceBundle;

public class DirectoryView implements FxmlView<DirectoryViewModel>, Initializable {
    @FXML
    private TilePane filesTiles;
    @FXML
    private Label name;


    @InjectViewModel
    DirectoryViewModel directoryViewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.textProperty().bind(directoryViewModel.directoryNameProperty());
        directoryViewModel.sourceFileViewModelsProperty().addListener(new ListChangeListener<SourceFile>() {
            @Override
            public void onChanged(Change<? extends SourceFile> c) {
                if (c.next())
                    c.getAddedSubList().forEach(sourceFile -> {
                        ViewTuple<SourceFileView, SourceFileViewModel> viewTuple = FluentViewLoader.fxmlView(SourceFileView.class).load();
                        viewTuple.getViewModel().initWithModel(sourceFile);
                        filesTiles.getChildren().add(viewTuple.getView());
                    });
            }
        });
    }
}
