package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import main.body.BodyController;

import java.util.Locale;
import java.util.ResourceBundle;

public class MainController {

    ResourceBundle mainBundle;

    @FXML
    public GridPane mainPane;

    @FXML
    private BodyController bodyController;

    @FXML
    public void onStart() throws Exception {
        this.initBody();
    }

    private void initBody() throws Exception {
        GridPane body = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/body/body.fxml"), this.mainBundle);
            body = loader.load();
            this.bodyController = loader.getController();
        } catch(Exception exc) {
            System.out.println(exc.getMessage() + " : " + exc.getCause());
        }
        if(body != null) {
            this.mainPane.getChildren().setAll(body);
            body.prefWidthProperty().bind(main.get_currentScene().widthProperty());
            body.prefHeightProperty().bind(main.get_currentScene().heightProperty());
        } else {
            throw new Exception("Can not load body");
        }
    }
}
