package main.body.content;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import main.body.content.info.InfoController;
import main.body.content.input.InputController;
import main.body.content.output.OutputController;
import classes.content.enums.ContentState;

public class ContentController implements Initializable {

    private content model = new content();

    private InfoController infoController = null;
    private InputController inputController = null;
    private OutputController outputController = null;

    @FXML
    public HBox contentBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            this.initContent(ContentState.INFO, resourceBundle);
        } catch (Exception exc)
        {
            System.out.println("Error in BodyController@initialize(): " + exc.getMessage() +
                    "(" + exc.getLocalizedMessage() +")" + " - " + exc.getCause());
        }
    }

    private void initContent(ContentState state, ResourceBundle resourceBundle) throws Exception {
        GridPane content = null;
        try {
            String resourceString = this.getResourceString(state);
            if (resourceString == null)
                throw new AssertionError("Cannot get resource string");
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resourceString), resourceBundle);
            content = loader.load();
            if(state == ContentState.INFO) {
                this.infoController = loader.getController();
            } else if (state == ContentState.INPUT) {
                this.inputController = loader.getController();
            } else if (state == ContentState.OUTPUT) {
                this.outputController = loader.getController();
            }
        } catch(Exception exc) {
            System.out.println(exc.getMessage()+ " : " + exc.getCause());
        }
        if(content != null) {
            this.contentBox.getChildren().setAll(content);
            this.model.state = state;
        } else {
            throw new Exception("Content can not be loaded");
        }
    }

    private String getResourceString(ContentState state) {
        switch (state) {
            case INFO:
                return "/main/body/content/info/info.fxml";
            case INPUT:
                return "/main/body/content/info/input.fxml";
            case OUTPUT:
                return "/main/body/content/info/output.fxml";
            default:
                return null;
        }
    }
}
