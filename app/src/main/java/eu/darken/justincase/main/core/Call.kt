package eu.darken.justincase.main.core

import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class Call(val time: Date, val number: String)