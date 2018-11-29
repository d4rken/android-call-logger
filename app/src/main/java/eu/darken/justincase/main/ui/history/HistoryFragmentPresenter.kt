package eu.darken.justincase.main.ui.history

import eu.darken.justincase.main.core.Call
import eu.darken.justincase.main.core.CallRepo
import eu.darken.mvpbakery.base.Presenter
import eu.darken.mvpbakery.injection.ComponentPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HistoryFragmentComponent.Scope
class HistoryFragmentPresenter @Inject
internal constructor(private val callRepo: CallRepo) : ComponentPresenter<HistoryFragmentPresenter.View, HistoryFragmentComponent>() {
    val hasPermission = true
    private var callRepoSub: Disposable = Disposables.disposed()
    override fun onBindChange(view: View?) {
        super.onBindChange(view)

        if (getView() != null || callRepoSub.isDisposed) {
            callRepoSub = callRepo.getCalls()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map { it.toMutableList() }
                    .map {
                        it.sortBy { call -> call.time }
                        it.reverse()
                        return@map it
                    }
                    .subscribe { calls -> onView { it.updateLog(calls) } }
        } else {
            callRepoSub.dispose()
        }
    }

    interface View : Presenter.View {
        fun updateLog(logs: List<Call>)
    }
}
