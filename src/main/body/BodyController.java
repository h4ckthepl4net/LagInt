package main.body;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import main.body.header.HeaderController;
import main.body.content.ContentController;
import main.body.footer.FooterController;
import ObserverObservable.Observable;
import classes.header.HeaderEventHandler;

public class BodyController implements Initializable {

    private Observable headerEventListener = new Observable(new HeaderEventHandler(this));

    @FXML
    private GridPane body;

    @FXML
    private HeaderController headerController;
    @FXML
    private ContentController contentController;
    @FXML
    private FooterController footerController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.initHeader(resources);
            this.initContent(resources);
            this.initFooter(resources);
        } catch (Exception exc)
        {
            System.out.println("Error in BodyController@initialize(): " + exc.getMessage() +
                                "(" + exc.getLocalizedMessage() +")" + " - " + exc.getCause());
        }
    }

    public void onUnhandledEvent(Object o) {
        System.out.println("BodyController::onUnhandledEvent --- Got an unhandled event --- " + o.getClass().getSimpleName() + " ---");
        System.out.println(" --- " + o.toString() + " --- ");
    }

    private void initHeader(ResourceBundle resourceBundle) throws Exception {
        HBox header = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/body/header/header.fxml"),
                                               resourceBundle);
            header = loader.load();
            this.headerController = loader.getController();
        } catch(Exception exc) {
            System.out.println(exc.getMessage()+ " : " + exc.getCause());
        }
        if(header != null) {
            this.body.getChildren().add(header);
            GridPane.setColumnIndex(header, 0);
            GridPane.setRowIndex(header, 0);
        } else {
            throw new Exception("Header can not be loaded");
        }
        this.headerController.headerEventDispatcher.subscribe(this.headerEventListener);
    }
    private void initContent(ResourceBundle resourceBundle) throws Exception {
        HBox content = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/body/content/content.fxml"),
                                               resourceBundle);
            content = loader.load();
            this.contentController = loader.getController();
        } catch(Exception exc) {
            System.out.println(exc.getMessage()+ " : " + exc.getCause());
        }
        if(content != null) {
            this.body.getChildren().add(content);
            GridPane.setColumnIndex(content, 0);
            GridPane.setRowIndex(content, 1);
        } else {
            throw new Exception("Content can not be loaded");
        }
    }
    private void initFooter(ResourceBundle resourceBundle) throws Exception {
        HBox footer = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/body/footer/footer.fxml"),
                                               resourceBundle);
            footer = loader.load();
            this.footerController = loader.getController();
        } catch(Exception exc) {
            System.out.println(exc.getMessage() + " : " + exc.getCause());
        }
        if(footer != null) {
            this.body.getChildren().add(footer);
            GridPane.setColumnIndex(footer, 0);
            GridPane.setRowIndex(footer, 2);
        } else {
            throw new Exception("Footer can not be loaded");
        }
    }
}
