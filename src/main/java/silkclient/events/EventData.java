package silkclient.events;

import java.lang.reflect.Method;

public class EventData {


    public final Object source;
    public final Method target;
    public final byte priority;

    public EventData(Object s , Method t, byte p) {
        this.source = s;
        this.target = t;
        this.priority = p;
    }

}
