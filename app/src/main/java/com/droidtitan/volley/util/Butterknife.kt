package com.droidtitan.volley.util

import android.app.Activity
import android.app.Dialog
import android.app.Fragment
import android.support.v4.app
import android.view.View
import kotlin.properties.ReadOnlyProperty
import android.support.v4.app.Fragment as SupportFragment

public fun <V : View> View.bindView(id: Int)
        : ReadOnlyProperty<View, V> = required(id, ::findViewById)

public fun <V : View> Activity.bindView(id: Int)
        : ReadOnlyProperty<Activity, V> = required(id, ::findViewById)

public fun <V : View> Dialog.bindView(id: Int)
        : ReadOnlyProperty<Dialog, V> = required(id, ::findViewById)

public fun <V : View> Fragment.bindView(id: Int)
        : ReadOnlyProperty<Fragment, V> = required(id, ::findViewById)

public fun <V : View> app.Fragment.bindView(id: Int)
        : ReadOnlyProperty<app.Fragment, V> = required(id, ::findViewById)

public fun <V : View> View.bindOptionalView(id: Int)
        : ReadOnlyProperty<View, V?> = optional(id, ::findViewById)

public fun <V : View> Activity.bindOptionalView(id: Int)
        : ReadOnlyProperty<Activity, V?> = optional(id, ::findViewById)

public fun <V : View> Dialog.bindOptionalView(id: Int)
        : ReadOnlyProperty<Dialog, V?> = optional(id, ::findViewById)

public fun <V : View> Fragment.bindOptionalView(id: Int)
        : ReadOnlyProperty<Fragment, V?> = optional(id, ::findViewById)

public fun <V : View> app.Fragment.bindOptionalView(id: Int)
        : ReadOnlyProperty<app.Fragment, V?> = optional(id, ::findViewById)

public fun <V : View> View.bindViews(vararg ids: Int)
        : ReadOnlyProperty<View, List<V>> = required(ids, ::findViewById)

public fun <V : View> Activity.bindViews(vararg ids: Int)
        : ReadOnlyProperty<Activity, List<V>> = required(ids, ::findViewById)

public fun <V : View> Dialog.bindViews(vararg ids: Int)
        : ReadOnlyProperty<Dialog, List<V>> = required(ids, ::findViewById)

public fun <V : View> Fragment.bindViews(vararg ids: Int)
        : ReadOnlyProperty<Fragment, List<V>> = required(ids, ::findViewById)

public fun <V : View> app.Fragment.bindViews(vararg ids: Int)
        : ReadOnlyProperty<app.Fragment, List<V>> = required(ids, ::findViewById)

public fun <V : View> View.bindOptionalViews(vararg ids: Int)
        : ReadOnlyProperty<View, List<V>> = optional(ids, ::findViewById)

public fun <V : View> Activity.bindOptionalViews(vararg ids: Int)
        : ReadOnlyProperty<Activity, List<V>> = optional(ids, ::findViewById)

public fun <V : View> Dialog.bindOptionalViews(vararg ids: Int)
        : ReadOnlyProperty<Dialog, List<V>> = optional(ids, ::findViewById)

public fun <V : View> Fragment.bindOptionalViews(vararg ids: Int)
        : ReadOnlyProperty<Fragment, List<V>> = optional(ids, ::findViewById)

public fun <V : View> app.Fragment.bindOptionalViews(vararg ids: Int)
        : ReadOnlyProperty<app.Fragment, List<V>> = optional(ids, ::findViewById)

private fun Fragment.findViewById(id: Int): View? = getView().findViewById(id)
private fun app.Fragment.findViewById(id: Int): View? = getView().findViewById(id)

private fun viewNotFound(id: Int, desc: PropertyMetadata) =
        throw IllegalStateException("View ID $id for '${desc.name}' not found.")

suppress("UNCHECKED_CAST")
private fun required<T, V : View>(id: Int, finder: T.(Int) -> View?)
        = Lazy { t: T, desc -> t.finder(id) as V? ?: viewNotFound(id, desc) }

suppress("UNCHECKED_CAST")
private fun optional<T, V : View>(id: Int, finder: T.(Int) -> View?)
        = Lazy { t: T, desc -> t.finder(id) as V? }

suppress("UNCHECKED_CAST")
private fun required<T, V : View>(ids: IntArray, finder: T.(Int) -> View?)
        = Lazy { t: T, desc -> ids.map { t.finder(it) as V? ?: viewNotFound(it, desc) } }

suppress("UNCHECKED_CAST")
private fun optional<T, V : View>(ids: IntArray, finder: T.(Int) -> View?)
        = Lazy { t: T, desc -> ids.map { t.finder(it) as V? }.filterNotNull() }

// Like Kotlin's lazy delegate but the initializer gets the target and metadata passed to it
private class Lazy<T, V>(private val initializer: (T, PropertyMetadata) -> V) : ReadOnlyProperty<T, V> {
    private object EMPTY

    private var value: Any? = EMPTY

    override fun get(thisRef: T, desc: PropertyMetadata): V {
        if (value == EMPTY) {
            value = initializer(thisRef, desc)
        }
        @suppress("UNCHECKED_CAST")
        return value as V
    }
}