package main.body.content;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

import classes.common.classes.AnsiUtils;
import classes.common.classes.BaseController;
import classes.content.enums.ContentState;

public class ContentController extends BaseController {

    public content model = new content();

    private BaseController currentContentController = null;

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        super.initialize(location, resourceBundle);
        try {
            boolean dont_show_info_again = this.classPrefs.node("info").getBoolean("dont_show_info_again", false);
            this.initContent(dont_show_info_again ? ContentState.INPUT : ContentState.INFO);
        } catch (Exception exc)
        {
            System.out.println(AnsiUtils.ANSI_RED +
                    "Error in ContentController@initialize(): " +
                    exc.getMessage() + "(" + exc.getLocalizedMessage() +") - " + exc.getCause() +
                    AnsiUtils.ANSI_RESET);
        }
    }

    public void initContent(ContentState state) throws Exception {
        GridPane content = null;
        try {
            String resourceString = this.getResourceString(state);
            if (resourceString == null)
                throw new AssertionError("Cannot get resource string");
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resourceString));
            content = loader.load();
            this.currentContentController = loader.getController();
        } catch(Exception exc) {
            System.out.println(AnsiUtils.ANSI_RED +
                    "Error in ContentController@initContent(): " + exc.getMessage()+ " : " + exc.getCause() +
                    AnsiUtils.ANSI_RESET);
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
                return "/main/body/content/input/input.fxml";
            case OUTPUT:
                return "/main/body/content/output/output.fxml";
            default:
                return null;
        }
    }
}
