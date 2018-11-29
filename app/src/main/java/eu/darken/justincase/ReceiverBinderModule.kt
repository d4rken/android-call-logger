package eu.darken.justincase

import android.content.BroadcastReceiver

import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.BroadcastReceiverKey
import dagger.multibindings.IntoMap
import eu.darken.justincase.main.core.receiver.CallReceiver
import eu.darken.justincase.main.core.receiver.CallReceiverComponent

@Module(subcomponents = arrayOf(CallReceiverComponent::class))
internal abstract class ReceiverBinderModule {

    @Binds
    @IntoMap
    @BroadcastReceiverKey(CallReceiver::class)
    internal abstract fun exampleReceiver(impl: CallReceiverComponent.Builder): AndroidInjector.Factory<out BroadcastReceiver>

}