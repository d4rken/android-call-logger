package eu.darken.justincase

import android.app.Activity
import android.app.Application
import android.content.BroadcastReceiver
import eu.darken.mvpbakery.injection.ComponentSource
import eu.darken.mvpbakery.injection.ManualInjector
import eu.darken.mvpbakery.injection.activity.HasManualActivityInjector
import eu.darken.mvpbakery.injection.broadcastreceiver.HasManualBroadcastReceiverInjector
import timber.log.Timber
import javax.inject.Inject


open class App : Application(), HasManualActivityInjector, HasManualBroadcastReceiverInjector {

    lateinit var activityInjector: ManualInjector<Activity>
    @Inject lateinit var appComponent: AppComponent
    @Inject lateinit var receiverInjector: ComponentSource<BroadcastReceiver>

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        DaggerAppComponent.builder()
                .androidModule(AndroidModule(this))
                .build()
                .injectMembers(this)

        activityInjector = appComponent.activityInjector()

        Timber.tag(TAG).d("onCreate() done!")
    }

    override fun activityInjector(): ManualInjector<Activity> {
        return activityInjector
    }

    override fun broadcastReceiverInjector(): ManualInjector<BroadcastReceiver> {
        return receiverInjector
    }

    companion object {
        internal val TAG = App.logTag("ExampleApp")

        fun logTag(vararg tags: String): String {
            val sb = StringBuilder()
            for (i in tags.indices) {
                sb.append(tags[i])
                if (i < tags.size - 1) sb.append(":")
            }
            return sb.toString()
        }
    }
}
