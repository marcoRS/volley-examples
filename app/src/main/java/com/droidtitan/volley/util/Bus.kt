package com.droidtitan.volley.util

import de.greenrobot.event.EventBus

/** A singleton object of Bus, this is created using. "object" instead of class. */
public object Bus {

    private val DEFAULT_BUS = EventBus.getDefault()

    public fun register(target: Any): Unit = DEFAULT_BUS.register(target)

    public fun unregister(target: Any): Unit = DEFAULT_BUS.unregister(target)

    public fun post(event: Any): Unit = DEFAULT_BUS.post(event)

}
