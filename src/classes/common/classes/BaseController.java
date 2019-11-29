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
        Pattern pattern = Pattern.compile("['\"]<&\\$.*\\$&>['\"]");
        for(Object child : this.mainPane.getChildrenUnmodifiable()) {
            String childAsString = child.toString();
            Matcher matcher = pattern.matcher(childAsString);
            if(matcher.find()) {
                String foundStr = matcher.group();
                Class<?> cls = child.getClass();
                Method textPropertyMethod;
                try {
                    textPropertyMethod = cls.getMethod("textProperty");
                    ((StringProperty)textPropertyMethod.invoke(child))
                            .bind(LocaleBindingFactory.getBinding(foundStr.substring(4, foundStr.length()-4)));
                } catch(NoSuchMethodException e) {
                    System.out.println("Cannot get method textProperty");//TODO
                }  catch(IllegalAccessException e) {
                    System.out.println("textProperty method is not accessible");//TODO
                } catch (InvocationTargetException e) {
                    System.out.println("textProperty method threw an exception");//TODO
                }
            }
        }
    }
}
