package eu.darken.justincase.main.ui


import android.support.v4.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap
import eu.darken.justincase.main.ui.history.HistoryFragment
import eu.darken.justincase.main.ui.history.HistoryFragmentComponent
import eu.darken.mvpbakery.injection.PresenterComponent
import eu.darken.mvpbakery.injection.activity.ActivityComponent

@MainActivityComponent.Scope
@Subcomponent(modules = arrayOf(MainActivityComponent.FragmentBinderModule::class))
interface MainActivityComponent : ActivityComponent<MainActivity>, PresenterComponent<MainActivityPresenter.View, MainActivityPresenter> {

    @Subcomponent.Builder
    abstract class Builder : ActivityComponent.Builder<MainActivity, MainActivityComponent>()

    @javax.inject.Scope
    @Retention(AnnotationRetention.RUNTIME)
    annotation class Scope

    @Module(subcomponents = arrayOf(HistoryFragmentComponent::class))
    abstract class FragmentBinderModule {

        @Binds
        @IntoMap
        @FragmentKey(HistoryFragment::class)
        internal abstract fun exampleFragment(impl: HistoryFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

    }
}
