package com.sunrise.dental.observer;
import java.util.*;
public class EventManager {
    private final List<EventListener> listeners=new ArrayList<>();
    public void subscribe(EventListener l) {
        listeners.add(l);
    }
    public void notifyAll(String e,String d) {
        listeners.forEach(x->x.onEvent(e,d));
    }
}
