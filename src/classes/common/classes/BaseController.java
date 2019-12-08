package classes.common.classes;

import java.net.URL;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import classes.common.exceptions.NotBindableProperty;
import classes.common.exceptions.MethodsDontMatchException;

public abstract class BaseController implements Initializable {

    @FXML
    public Pane mainPane;

    protected Preferences classPrefs;

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        classPrefs = Preferences.userNodeForPackage(this.getClass());
        this.bind(this.mainPane);
    }

    private void bind(Pane bindableContainer) {
        List<String> primaryTextMethodNames = Arrays.asList("getPromptText", "getText");
        List<String> primaryTextPropertyNames = Arrays.asList("promptTextProperty", "textProperty");
        NodeHelper childNodeHelper = new NodeHelper();
        Pattern pattern = Pattern.compile("<&\\$.*\\$&>");
        ObservableList<Node> children = bindableContainer.getChildren();
        for(Node child : children) {
            if(Pane.class.isAssignableFrom(child.getClass())) {
                this.bind((Pane)child);
            } else {
                childNodeHelper.setChild(child);
                try {
                    Method primaryTextMethod = childNodeHelper.getFirstMatchedMethod(primaryTextMethodNames);
                    Method primaryTextProperty = childNodeHelper.getFirstMatchedMethod(primaryTextPropertyNames);
                    if(primaryTextMethodNames.indexOf(primaryTextMethod.getName()) !=
                       primaryTextPropertyNames.indexOf(primaryTextProperty.getName())) {
                        throw new MethodsDontMatchException("Methods found from the given array don't match");
                    }
                    String text = (String)childNodeHelper.invokeMethodAndHandleExceptions(primaryTextMethod);
                    Matcher matcher = pattern.matcher(text);
                    if (!matcher.matches()) {
                        throw new NotBindableProperty("Found text property is not bindable");
                    }
                    String foundStr = matcher.group();
                    ((StringProperty) childNodeHelper.invokeMethodAndHandleExceptions(primaryTextProperty))
                            .bind(LocaleBindingFactory.getBinding(foundStr.substring(3, foundStr.length() - 3)));
                } catch (Exception exc) {
                    System.out.println(AnsiUtils.ANSI_RED +
                            "Error in BaseController@bind(): " +
                            exc.getMessage() + " (" + exc.getLocalizedMessage() +") - " + exc.getCause() +
                            AnsiUtils.ANSI_RESET);
                }
            }
        }
    }
}
