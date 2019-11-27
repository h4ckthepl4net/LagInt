package classes.header;

import java.util.Locale;
import java.util.ResourceBundle;

import ObserverObservable.Interface.ObservableCallBackReceiver;

import classes.common.classes.LocaleBindingFactory;
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
        switch (changeTo) {
            case LANGUAGE_AM:
                LocaleBindingFactory.setResources(ResourceBundle.getBundle("bundles/locale", new Locale("hy")));
                break;
            case LANGUAGE_EN:
                LocaleBindingFactory.setResources(ResourceBundle.getBundle("bundles/locale", new Locale("en")));
                break;
            case LANGUAGE_RU:
                LocaleBindingFactory.setResources(ResourceBundle.getBundle("bundles/locale", new Locale("ru")));
                break;
            default:
                //TODO add logging
                break;
        }
    }

    private void changeTheme(ThemeChangeType changeTo) {

    }

}
