package main.body.content;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

import classes.common.classes.BaseController;
import classes.content.enums.ContentState;

public class ContentController extends BaseController {

    private content model = new content();

    private BaseController currentContentController = null;

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        super.initialize(location, resourceBundle);
        try {
            this.initContent(ContentState.INFO);
        } catch (Exception exc)
        {
            System.out.println("Error in BodyController@initialize(): " + exc.getMessage() +
                    "(" + exc.getLocalizedMessage() +")" + " - " + exc.getCause());
        }
    }

    private void initContent(ContentState state) throws Exception {
        GridPane content = null;
        try {
            String resourceString = this.getResourceString(state);
            if (resourceString == null)
                throw new AssertionError("Cannot get resource string");
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resourceString));
            content = loader.load();
            this.currentContentController = loader.getController();
        } catch(Exception exc) {
            System.out.println(exc.getMessage()+ " : " + exc.getCause());
        }
        if(content != null) {
            ((GridPane)mainPane).getChildren().setAll(content);
            this.model.state = state;
            GridPane.setColumnIndex(content, 0);
            GridPane.setRowIndex(content, 0);
        } else {
            throw new Exception("Content with state " + state.name() + " can not be loaded");
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
