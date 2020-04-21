package com.remych04.overeating.self.helping.base.ext

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun <T> Fragment.binding(initialise: () -> T): ReadOnlyProperty<Fragment, T> {
    return ViewBindingFragment(this, initialise)
}

private class ViewBindingFragment<T>(
    fragment: Fragment,
    private val initialise: () -> T
) : ReadOnlyProperty<Fragment, T>, LifecycleObserver {

    private var binding: T? = null

    init {
        fragment.viewLifecycleOwnerLiveData.observe(fragment, Observer {
            it.lifecycle.addObserver(this)
        })
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        return binding ?: initialise().also {
            binding = it
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        binding = null
    }
}
