package com.droidtitan.volleyexamples.rest.util;

import de.greenrobot.event.EventBus;

public final class Bus {
    private Bus() {
    }

    private static final EventBus DEFAULT_BUS = EventBus.getDefault();

    public static void register(Object target) {
        DEFAULT_BUS.register(target);
    }

    public static void unregister(Object target) {
        DEFAULT_BUS.unregister(target);
    }

    public static void post(Object event) {
        DEFAULT_BUS.post(event);
    }

}
