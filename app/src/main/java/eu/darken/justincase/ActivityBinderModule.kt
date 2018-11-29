package eu.darken.justincase

import android.app.Activity

import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import eu.darken.justincase.main.ui.MainActivity
import eu.darken.justincase.main.ui.MainActivityComponent

@Module(subcomponents = arrayOf(MainActivityComponent::class))
internal abstract class ActivityBinderModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    internal abstract fun main(impl: MainActivityComponent.Builder): AndroidInjector.Factory<out Activity>
}