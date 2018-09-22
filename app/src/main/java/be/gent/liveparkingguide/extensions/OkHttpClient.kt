package be.gent.liveparkingguide.extensions

import be.gent.liveparkingguide.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.*

fun OkHttpClient.Builder.addLoggingIfDebug(
        level: HttpLoggingInterceptor.Level = BASIC
): OkHttpClient.Builder {

    if (BuildConfig.DEBUG)
        addInterceptor(HttpLoggingInterceptor().setLevel(level))

    return this
}