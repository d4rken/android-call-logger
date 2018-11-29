package eu.darken.justincase.main.ui.history

import dagger.Subcomponent
import eu.darken.mvpbakery.injection.PresenterComponent
import eu.darken.mvpbakery.injection.fragment.FragmentComponent


@HistoryFragmentComponent.Scope
@Subcomponent
interface HistoryFragmentComponent : PresenterComponent<HistoryFragmentPresenter.View, HistoryFragmentPresenter>, FragmentComponent<HistoryFragment> {
    @Subcomponent.Builder
    abstract class Builder : FragmentComponent.Builder<HistoryFragment, HistoryFragmentComponent>()

    @MustBeDocumented
    @javax.inject.Scope
    @Retention(AnnotationRetention.RUNTIME)
    annotation class Scope
}
