package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

import classes.common.classes.AnsiUtils;
import classes.common.classes.BaseController;

import main.body.BodyController;

public class MainController extends BaseController {

    @FXML
    private BodyController bodyController;

    @FXML
    public void onStart() throws Exception {
        this.initBody();
    }

    private void initBody() throws Exception {
        GridPane body = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/body/body.fxml"));
            body = loader.load();
            this.bodyController = loader.getController();
        } catch(Exception exc) {
            System.out.println(AnsiUtils.ANSI_RED +
                    "Error in MainController@initBody(): " + exc.getMessage() + " : " + exc.getCause() +
                    AnsiUtils.ANSI_RESET);
        }
        if(body != null) {
            ((GridPane)this.mainPane).getChildren().setAll(body);
            body.prefWidthProperty().bind(main.get_currentScene().widthProperty());
            body.prefHeightProperty().bind(main.get_currentScene().heightProperty());
        } else {
            throw new Exception("Can not load body");
        }
    }
}
