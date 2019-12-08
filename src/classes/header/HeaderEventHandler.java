package classes.header;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import ObserverObservable.Interface.ObservableCallBackReceiver;

import classes.common.classes.AnsiUtils;
import classes.common.classes.LocaleBindingFactory;
import classes.header.enums.LanguageChangeType;
import classes.header.enums.LocationChangeType;
import classes.header.enums.ThemeChangeType;
import classes.content.enums.ContentState;

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
        System.out.println(AnsiUtils.ANSI_BLUE +
                "HeaderEventHandler@onComplete(): Stream with id " + streamId + " has been completed" +
                AnsiUtils.ANSI_RESET);
    }

    @Override
    public void onCancel(int streamId) {
        System.out.println(AnsiUtils.ANSI_BLUE +
                "HeaderEventHandler@onCancel(): Stream with id " + streamId + " has been canceled" +
                AnsiUtils.ANSI_RESET);
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
        int currentStateOrdinal = this.bodyController.contentController.model.state.ordinal();
        ContentState nextState;
        boolean dont_show_info_again = Preferences.userRoot().node("main/body/content/info")
                                        .getBoolean("dont_show_info_again", false);
        try {
            switch (changeTo) {
                case GO_BACKWARD:
                    nextState = ContentState.values()[currentStateOrdinal-1];
                    if(nextState == ContentState.INFO && !dont_show_info_again ||
                        nextState == ContentState.INPUT && dont_show_info_again) {
                        this.bodyController.headerController.isBackButtonDisabled.set(true);
                    }
                    this.bodyController.headerController.isNextButtonDisabled.set(false);
                    this.bodyController.contentController.initContent(nextState);
                    break;
                case GO_FORWARD:
                    nextState = ContentState.values()[currentStateOrdinal+1];
                    if(nextState == ContentState.OUTPUT) {
                        this.bodyController.headerController.isNextButtonDisabled.set(true);
                    }
                    this.bodyController.headerController.isBackButtonDisabled.set(nextState == ContentState.INPUT && dont_show_info_again);
                    this.bodyController.contentController.initContent(nextState);
                    break;
                default:
                    System.out.println(AnsiUtils.ANSI_YELLOW +
                            "Warning in HeaderEventHandler@changeLocation(): No change detected" +
                            AnsiUtils.ANSI_RESET);
                    break;
            }
        } catch (Exception exc) {
            System.out.println(AnsiUtils.ANSI_RED +
                    "Error in HeaderEventHandler@changeLocation(): " +
                    exc.getMessage() + " (" + exc.getLocalizedMessage() +") - " + exc.getCause() +
                    AnsiUtils.ANSI_RESET);
        }
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
                System.out.println(AnsiUtils.ANSI_YELLOW +
                        "Warning in HeaderEventHandler@changeLanguage(): No change detected" +
                        AnsiUtils.ANSI_RESET);
                break;
        }
    }

    private void changeTheme(ThemeChangeType changeTo) {

    }

}
