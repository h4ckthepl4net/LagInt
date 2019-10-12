package main.body;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import main.body.content.ContentController;
import main.body.footer.FooterController;
import main.body.header.HeaderController;

import java.net.URL;
import java.util.ResourceBundle;

public class BodyController implements Initializable {

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
            this.initHeader();
            this.initContent();
            this.initFooter();
        } catch (Exception exc)
        {
            System.out.println("Error in BodyController@initialize(): " + exc.getMessage() +
                                "(" + exc.getLocalizedMessage() +")" + " - " + exc.getCause());
        }
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
            this.body.getChildren().add(header);
            GridPane.setColumnIndex(header, 0);
            GridPane.setRowIndex(header, 0);
        } else {
            throw new Exception("Header can not be loaded");
        }
    }
    private void initContent() throws Exception {
        HBox content = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/body/content/content.fxml"));
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
    private void initFooter() throws Exception {
        HBox footer = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/body/footer/footer.fxml"));
            footer = loader.load();
            this.footerController = loader.getController();
        } catch(Exception exc) {
            System.out.println(exc.getMessage()+ " : " + exc.getCause());
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
