package main.body.header;

import ObserverObservable.Observer;
import classes.header.HeaderEvent;
import classes.header.enums.EventType;
import classes.header.enums.LocationChangeType;
import javafx.fxml.FXML;

public class HeaderController {

    public Observer headerEventDispatcher = new Observer();
    private header model = new header();

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