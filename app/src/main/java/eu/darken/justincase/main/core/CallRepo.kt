package eu.darken.justincase.main.core

import android.content.Context
import com.squareup.moshi.Moshi
import eu.darken.justincase.AppComponent
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber
import java.io.File
import java.util.*
import javax.inject.Inject


@AppComponent.Scope
class CallRepo @Inject
constructor(moshi: Moshi, context: Context) {
    private val storagePath = File(context.filesDir, "calls")
    private val adapter = moshi.adapter(Call::class.java)
    private val notifer = BehaviorSubject.createDefault(true)

    init {
        storagePath.mkdir()
    }

    fun saveCall(number: String): Single<Call> =
            Single.just(number)
                    .map { Call(Date(), number) }
                    .doOnSuccess {
                        val json = adapter.toJson(it)
                        val file = File(storagePath, "${it.time.time}.json")
                        file.writeText(json)
                    }
                    .doOnSuccess { notifer.onNext(true) }
                    .doOnSuccess { Timber.d("Saved $it") }
                    .doOnError { Timber.e(it, "Failed to save $number") }


    fun getCalls(): Observable<List<Call>> =
            notifer.map {
                val calls = mutableListOf<Call>()
                storagePath.listFiles().forEach { file ->
                    calls.add(adapter.fromJson(file.readText())!!)
                }
                return@map calls
            }
}
