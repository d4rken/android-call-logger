package eu.darken.justincase.main.ui

import eu.darken.mvpbakery.base.Presenter
import eu.darken.mvpbakery.injection.ComponentPresenter
import javax.inject.Inject

class MainActivityPresenter @Inject
constructor() : ComponentPresenter<MainActivityPresenter.View, MainActivityComponent>() {

    override fun onBindChange(view: View?) {
        super.onBindChange(view)
        onView { v -> v.showExampleFragment() }
    }

    interface View : Presenter.View {
        fun showExampleFragment()
    }
}
