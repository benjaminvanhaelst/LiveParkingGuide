package be.gent.liveparkingguide.webservices

import be.gent.liveparkingguide.model.Parking
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 * Created by bvanhaelst
 */
interface ParkingsRealtime {

    @Headers("Accept: application/json;charset=UTF-8")
    @GET("bezettingparkingsrealtime")
    fun parkings() : Call<ArrayList<Parking>>
}