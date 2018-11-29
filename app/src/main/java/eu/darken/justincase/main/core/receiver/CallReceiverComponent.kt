package eu.darken.justincase.main.core.receiver


import dagger.Subcomponent
import eu.darken.mvpbakery.injection.broadcastreceiver.BroadcastReceiverComponent

@CallReceiverComponent.Scope
@Subcomponent
interface CallReceiverComponent : BroadcastReceiverComponent<CallReceiver> {

    @Subcomponent.Builder
    abstract class Builder : BroadcastReceiverComponent.Builder<CallReceiver, CallReceiverComponent>()

    @javax.inject.Scope
    @Retention(AnnotationRetention.RUNTIME)
    annotation class Scope
}
