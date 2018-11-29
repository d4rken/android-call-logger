package eu.darken.justincase.main.ui.history

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import eu.darken.justincase.R
import eu.darken.justincase.common.smart.SmartFragment
import eu.darken.justincase.main.core.Call
import eu.darken.mvpbakery.base.MVPBakery
import eu.darken.mvpbakery.base.ViewModelRetainer
import eu.darken.mvpbakery.injection.InjectedPresenter
import eu.darken.mvpbakery.injection.PresenterInjectionCallback
import javax.inject.Inject


class HistoryFragment : SmartFragment(), HistoryFragmentPresenter.View {

    companion object {
        fun newInstance(): Fragment {
            return HistoryFragment()
        }
    }

    @BindView(R.id.toolbar) lateinit var toolbar: Toolbar
    @BindView(R.id.recyclerview) lateinit var recyclerView: RecyclerView
    private val adapter = HistoryAdapter()
    @Inject lateinit var presenter: HistoryFragmentPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.history_fragment, container, false)
        addUnbinder(ButterKnife.bind(this, layout))
        return layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        MVPBakery.builder<HistoryFragmentPresenter.View, HistoryFragmentPresenter>()
                .presenterFactory(InjectedPresenter(this))
                .presenterRetainer(ViewModelRetainer(this))
                .addPresenterCallback(PresenterInjectionCallback(this))
                .attach(this)

        super.onActivityCreated(savedInstanceState)

        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)
        toolbar.setTitle(R.string.app_name)
    }

    override fun updateLog(logs: List<Call>) {
        adapter.data.clear()
        adapter.data.addAll(logs)
        adapter.notifyDataSetChanged()

        toolbar.subtitle = "${logs.size} entries"
    }
}
