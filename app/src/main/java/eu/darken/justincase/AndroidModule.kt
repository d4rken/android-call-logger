package eu.darken.justincase

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import eu.darken.justincase.common.moshi.DateAdapter


@Module
class AndroidModule(private val app: App) {

    @Provides
    @AppComponent.Scope
    fun context(): Context = app.applicationContext

    @Provides
    @AppComponent.Scope
    fun preferences(context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @AppComponent.Scope
    fun moshi(): Moshi =
            Moshi.Builder()
                    .add(DateAdapter())
                    .build()
}
