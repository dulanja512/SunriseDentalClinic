package com.sunrise.dental.observer;
public class SystemNotificationListener implements EventListener {
    public void onEvent(String e,String d) {
        System.out.println("EVENT: "+e+" - "+d);
    }
}
