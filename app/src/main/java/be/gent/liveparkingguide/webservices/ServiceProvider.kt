package be.gent.liveparkingguide.webservices

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor.Level.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by bvanhaelst
 */
object ServiceProvider {


    fun provideGson() : Gson
            = Gson()

    fun provideClient() : OkHttpClient
            = OkHttpClient.Builder()
            .addLoggingIfDebug(BODY)
            .build()

    fun provideParkingsRealTime() : ParkingsRealtime
            = Retrofit.Builder()
            .baseUrl("https://datatank.stad.gent/4/mobiliteit/")
            .client(provideClient())
            .addConverterFactory(GsonConverterFactory.create(provideGson()))
            .build().create(ParkingsRealtime::class.java)
}