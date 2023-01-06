import View.MainView;
import ViewModel.MainViewModel;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.IOException;
import java.util.Objects;

public class Starter extends Application {
    public static void main(String...args){
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        ViewTuple<MainView, MainViewModel> viewTuple = FluentViewLoader.fxmlView(MainView.class).load();
        Scene mainScene = new Scene(viewTuple.getView(), 1270, 920);
        mainScene.getStylesheets().add(Starter.class.getResource("View/RibbonStyle.css").toExternalForm());

        new JMetro(mainScene, Style.LIGHT);
        stage.setScene(mainScene);
        stage.show();
    }
}