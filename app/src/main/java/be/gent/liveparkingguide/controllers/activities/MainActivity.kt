package be.gent.liveparkingguide.controllers.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import be.gent.liveparkingguide.R
import be.gent.liveparkingguide.controllers.adapters.ParkingListAdapter
import be.gent.liveparkingguide.webservices.ParkingsRealtime
import be.gent.liveparkingguide.webservices.ServiceProvider
import be.gent.liveparkingguide.extensions.callback
import be.gent.liveparkingguide.extensions.start
import be.gent.liveparkingguide.extensions.stop
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.support.v4.onRefresh
import be.gent.liveparkingguide.extensions.launchActivity


class MainActivity : AppCompatActivity() {

    private val parkingsRealtime:  ParkingsRealtime by lazy { ServiceProvider.provideParkingsRealTime() }

    private val adapter : ParkingListAdapter by lazy {
        ParkingListAdapter { parking ->

            launchActivity<MapActivity> {
                putExtra(MapActivity.PARKING_EXTRA, parking)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_parking.layoutManager = LinearLayoutManager(baseContext)
        rv_parking.adapter = adapter

        sr_parking.onRefresh {
           fetch()
        }

    }

    override fun onResume() {
        super.onResume()
        sr_parking.start()
        fetch()
    }

    private fun fetch() {
        parkingsRealtime.parkings().enqueue(
                callback(onSuccess = {
                    adapter.setParkings(it)
                }, onEnd = {
                    sr_parking?.stop()
                }))

    }



}
