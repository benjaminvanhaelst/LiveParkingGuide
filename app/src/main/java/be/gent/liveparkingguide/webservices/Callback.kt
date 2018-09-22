package be.gent.liveparkingguide.webservices

import retrofit2.Call
import retrofit2.Callback

/**
 * Created by bvanhaelst
 */
fun <T> callback(onSuccess: (body: T) -> Unit = { }, onFailure  : (t : Throwable?) -> Unit = { } ): Callback<T> {

    return object : Callback<T> {
        override fun onResponse(call: Call<T>, response: retrofit2.Response<T>) {
            val body = response.body()
            if (body != null) onSuccess(body) else onFailure(null)
        }


        override fun onFailure(call: Call<T>, t: Throwable) {
            onFailure(t)
        }
    }
}