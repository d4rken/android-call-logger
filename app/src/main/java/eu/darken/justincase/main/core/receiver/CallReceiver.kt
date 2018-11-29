package eu.darken.justincase.main.core.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import eu.darken.justincase.main.core.CallRepo

import eu.darken.mvpbakery.injection.broadcastreceiver.HasManualBroadcastReceiverInjector
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class CallReceiver : BroadcastReceiver() {

    @Inject lateinit var callRepo: CallRepo

    override fun onReceive(context: Context, intent: Intent) {
        Timber.v("onReceive(%s, %s)", context, intent)
        (context.applicationContext as HasManualBroadcastReceiverInjector).broadcastReceiverInjector().inject(this)

        val number = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER)
        if (number != null) {
            val pendingResult = goAsync()
            callRepo.saveCall(number)
                    .subscribeOn(Schedulers.io())
                    .doFinally { pendingResult.finish() }
                    .subscribe()
        }
    }

}
