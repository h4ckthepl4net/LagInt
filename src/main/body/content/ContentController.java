package main.body.content;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import main.body.content.info.InfoController;
import main.body.content.input.InputController;
import main.body.content.output.OutputController;

import java.net.URL;
import java.util.ResourceBundle;

public class ContentController implements Initializable {

    public content model = new content();

    public InfoController infoController = null;
    public InputController inputController = null;
    public OutputController outputController = null;

    @FXML
    public HBox contentBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            this.initContent(content.ContentState.INFO);
        } catch (Exception exc)
        {
            System.out.println("Error in BodyController@initialize(): " + exc.getMessage() +
                    "(" + exc.getLocalizedMessage() +")" + " - " + exc.getCause());
        }
    }

    private void initContent(content.ContentState state) throws Exception {
        GridPane content = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(this.getResourceString(state)));
            content = loader.load();
            if(state == main.body.content.content.ContentState.INFO) {
                this.infoController = loader.getController();
            } else if (state == main.body.content.content.ContentState.INPUT) {
                this.inputController = loader.getController();
            } else if (state == main.body.content.content.ContentState.OUTPUT) {
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

    private String getResourceString(content.ContentState state) {
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
