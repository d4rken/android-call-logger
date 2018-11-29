package eu.darken.justincase.main.ui.history

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import eu.darken.justincase.R
import eu.darken.justincase.main.core.Call
import java.text.DateFormat

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    val data = mutableListOf<Call>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) = ViewHolder(p0)

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) = holder.bind(data[pos])

    class ViewHolder(parent: ViewGroup)
        : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.history_adapter_line, parent, false)) {
        @BindView(R.id.label) lateinit var label: TextView
        @BindView(R.id.info) lateinit var info: TextView

        init {
            ButterKnife.bind(this, itemView)
        }

        fun bind(call: Call) {
            label.text = DateFormat.getDateTimeInstance().format(call.time)
            info.text = call.number
        }
    }

}

