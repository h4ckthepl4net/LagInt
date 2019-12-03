package main.body.header;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.transformation.FilteredList;

import ObserverObservable.Observer;

import classes.common.classes.BaseController;
import classes.header.HeaderEvent;
import classes.header.enums.EventType;
import classes.header.enums.LocationChangeType;

public class HeaderController extends BaseController {

    public Observer headerEventDispatcher = new Observer();

    public SimpleBooleanProperty isBackButtonDisabled = new SimpleBooleanProperty(true);
    public SimpleBooleanProperty isNextButtonDisabled = new SimpleBooleanProperty(false);

    private header model = new header();

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        super.initialize(location, resourceBundle);
        FilteredList<Node> nodes = ((HBox)this.mainPane).getChildren().filtered(currentNode -> {
            String id = currentNode.getId();
            return id.equals("backButton") || id.equals("nextButton");
        });
        nodes.get(0).disableProperty().bind(this.isBackButtonDisabled);
        nodes.get(1).disableProperty().bind(this.isNextButtonDisabled);
    }

    @FXML
    public void onNext() {
        HeaderEvent event = new HeaderEvent(EventType.CHANGE_LOCATION, LocationChangeType.GO_FORWARD);
        headerEventDispatcher.emit(event);
    }
    @FXML
    public void onBack() {
        HeaderEvent event = new HeaderEvent(EventType.CHANGE_LOCATION, LocationChangeType.GO_BACKWARD);
        headerEventDispatcher.emit(event);
    }

}