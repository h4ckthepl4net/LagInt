package classes.header;

import ObserverObservable.Interface.ObservableCallBackReceiver;
import classes.header.enums.LanguageChangeType;
import classes.header.enums.LocationChangeType;
import classes.header.enums.ThemeChangeType;
import main.body.BodyController;

public class HeaderEventHandler implements ObservableCallBackReceiver {

    private BodyController bodyController;

    public HeaderEventHandler(BodyController bodyController) {
        this.bodyController = bodyController;
    }

    @Override
    public void onNext(Object o) {
        if(o.getClass().getSimpleName().equals("HeaderEvent")) {
            this.handleHeaderEvent((HeaderEvent)o);
        } else {
            this.bodyController.onUnhandledEvent(o);
        }
    }

    @Override
    public void onComplete(int streamId) {
        System.out.println("HeaderEventHandler::onComplete --- Stream with id " + streamId + " has been completed ---");
    }

    @Override
    public void onCancel(int streamId) {
        System.out.println("HeaderEventHandler::onComplete --- Stream with id " + streamId + " has been canceled ---");
    }

    private void handleHeaderEvent(HeaderEvent event) {
        switch(event.type) {
            case CHANGE_LOCATION:
                this.changeLocation((LocationChangeType)event.get_value());
                break;
            case CHANGE_LANGUAGE:
                this.changeLanguage((LanguageChangeType)event.get_value());
                break;
            case CHANGE_THEME:
                this.changeTheme((ThemeChangeType)event.get_value());
                break;
            default:
                this.bodyController.onUnhandledEvent(event);
                break;
        }
    }

    private void changeLocation(LocationChangeType changeTo) {

    }

    private void changeLanguage(LanguageChangeType changeTo) {

    }

    private void changeTheme(ThemeChangeType changeTo) {

    }

}
