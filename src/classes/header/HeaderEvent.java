package classes.header;

import classes.header.enums.EventType;

public class HeaderEvent {

    EventType type;
    private Object value;

    public HeaderEvent(EventType type, Object value) {
        this.type = type;
        this.value = value;
    }

    Object get_value() {
        return this.value;
    }
}
