package main.body;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;

import ObserverObservable.Observable;

import classes.header.HeaderEventHandler;
import classes.common.classes.BaseController;

import main.body.header.HeaderController;
import main.body.content.ContentController;
import main.body.footer.FooterController;

public class BodyController extends BaseController {

    private Observable headerEventListener = new Observable(new HeaderEventHandler(this));

    @FXML
    private HeaderController headerController;
    @FXML
    private ContentController contentController;
    @FXML
    private FooterController footerController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        try {
            this.initHeader();
            this.initContent();
            this.initFooter();
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

    private void initHeader() throws Exception {
        HBox header = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/body/header/header.fxml"));
            header = loader.load();
            this.headerController = loader.getController();
        } catch(Exception exc) {
            System.out.println(exc.getMessage()+ " : " + exc.getCause());
        }
        if(header != null) {
            ((GridPane)this.mainPane).getChildren().add(header);
            GridPane.setColumnIndex(header, 0);
            GridPane.setRowIndex(header, 0);
        } else {
            throw new Exception("Header can not be loaded");
        }
        this.headerController.headerEventDispatcher.subscribe(this.headerEventListener);
    }
    private void initContent() throws Exception {
        GridPane content = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/body/content/content.fxml"));
            content = loader.load();
            this.contentController = loader.getController();
        } catch(Exception exc) {
            System.out.println(exc.getMessage()+ " : " + exc.getCause());
        }
        if(content != null) {
            ((GridPane)this.mainPane).getChildren().add(content);
            GridPane.setColumnIndex(content, 0);
            GridPane.setRowIndex(content, 1);
        } else {
            throw new Exception("Content can not be loaded");
        }
    }
    private void initFooter() throws Exception {
        HBox footer = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/body/footer/footer.fxml"));
            footer = loader.load();
            this.footerController = loader.getController();
        } catch(Exception exc) {
            System.out.println(exc.getMessage() + " : " + exc.getCause());
        }
        if(footer != null) {
            ((GridPane)this.mainPane).getChildren().add(footer);
            GridPane.setColumnIndex(footer, 0);
            GridPane.setRowIndex(footer, 2);
        } else {
            throw new Exception("Footer can not be loaded");
        }
    }
}
