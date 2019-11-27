package classes.common.classes;

import java.util.ResourceBundle;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class LocaleBindingFactory {
    private static ObjectProperty<ResourceBundle> resources = new SimpleObjectProperty<>();

    private LocaleBindingFactory() {}

    public static ResourceBundle getResources() {
        return LocaleBindingFactory.resources.get();
    }

    public static void setResources(ResourceBundle resources) {
        LocaleBindingFactory.resources.set(resources);
    }

    static LocaleBinding getBinding(String key) {
        return new LocaleBinding(LocaleBindingFactory.resources, key);
    }
}
