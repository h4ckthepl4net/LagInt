package classes.common.classes;

import java.net.URL;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.beans.property.StringProperty;
import javafx.scene.Parent;

public abstract class BaseController implements Initializable {

    @FXML
    public Parent mainPane;

    protected Preferences classPrefs;

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        classPrefs = Preferences.userNodeForPackage(this.getClass());
        this.bind();
    }

    private void bind() {
        Pattern pattern = Pattern.compile("<&\\$.*\\$&>");
        for(Object child : this.mainPane.getChildrenUnmodifiable()) {
            Class<?> cls = child.getClass();
            Method getTextMethod = null, textPropertyMethod;
            try {
                getTextMethod = cls.getMethod("getText");
                String childText = (String)getTextMethod.invoke(child);
                Matcher matcher = pattern.matcher(childText);
                if (matcher.find()) {
                    String foundStr = matcher.group();
                    textPropertyMethod = cls.getMethod("textProperty");
                    ((StringProperty)textPropertyMethod.invoke(child))
                            .bind(LocaleBindingFactory.getBinding(foundStr.substring(3, foundStr.length()-3)));
                }
            } catch(NoSuchMethodException e) {
                System.out.println(AnsiUtils.ANSI_RED +
                        "Error in BaseController@bind(): Reflection can not get method " + e.getMessage() +
                        AnsiUtils.ANSI_RESET);
            }  catch(IllegalAccessException e) {
                System.out.println(AnsiUtils.ANSI_RED +
                        "Error in BaseController@bind(): Reflection can not access " +
                        child.getClass().getName() + ((getTextMethod == null) ? ".getText()" : ".textProperty()") +
                        AnsiUtils.ANSI_RESET);
            } catch (InvocationTargetException e) {
                System.out.println(AnsiUtils.ANSI_RED +
                        "Error in BaseController@bind(): Method " +
                        child.getClass().getName() + ((getTextMethod == null) ? ".getText()" : ".textProperty()") +
                        " threw an exception on invocation" +
                        AnsiUtils.ANSI_RESET);
            }
        }
    }
}
