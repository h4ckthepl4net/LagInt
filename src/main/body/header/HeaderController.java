package main.body.header;

import javafx.fxml.FXML;

public class HeaderController {

    private header model = new header();

    @FXML
    public void onNext() {
        System.out.println("next");
        this.model.history.add("next");
    }
    @FXML
    public void onBack() {
        System.out.println("back");
        this.model.history.add("back");
    }
}