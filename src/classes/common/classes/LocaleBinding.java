package classes.common.classes;

import java.util.ResourceBundle;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;

public class LocaleBinding extends StringBinding {

    private ObjectProperty<ResourceBundle> resource;
    private String key;

    LocaleBinding(ObjectProperty<ResourceBundle> resource, String key) {
        this.resource = resource;
        this.key = key;
        bind(resource);
    }
    @Override
    protected String computeValue() {
        return this.resource.get().getString(this.key);
    }
}
