package be.gent.liveparkingguide.extensions

import retrofit2.Call
import retrofit2.Callback

fun <T> callback(onSuccess: (body: T) -> Unit = { }, onEnd  : () -> Unit = { }, onFailure  : (t : Throwable?) -> Unit = { } ): Callback<T> {

    return object : Callback<T> {
        override fun onResponse(call: Call<T>, response: retrofit2.Response<T>) {
            val body = response.body()
            onEnd()
            if (body != null) onSuccess(body) else onFailure(null)
        }


        override fun onFailure(call: Call<T>, t: Throwable) {
            onEnd()
            onFailure(t)

        }
    }
}