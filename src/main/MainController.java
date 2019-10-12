package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import main.body.BodyController;

public class MainController {

    @FXML
    public GridPane mainPane;

    @FXML
    private BodyController bodyController;

    @FXML
    public void onStart() throws Exception {
        GridPane body = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/body/body.fxml"));
            body = loader.load();
            this.bodyController = loader.getController();
        } catch(Exception exc) {
            System.out.println(exc.getMessage()+ " : " + exc.getCause());
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
