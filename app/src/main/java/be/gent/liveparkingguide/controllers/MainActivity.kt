package be.gent.liveparkingguide.controllers

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import be.gent.liveparkingguide.R
import be.gent.liveparkingguide.webservices.ParkingsRealtime
import be.gent.liveparkingguide.webservices.ServiceProvider
import be.gent.liveparkingguide.webservices.callback

class MainActivity : AppCompatActivity() {

    private val parkingsRealtime:  ParkingsRealtime by lazy { ServiceProvider.provideParkingsRealTime() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        parkingsRealtime.parkings().enqueue(
                callback( onSuccess = {
                            it.forEach {

                            }
                        }) )
    }



}
