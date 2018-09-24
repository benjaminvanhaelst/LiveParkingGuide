package be.gent.liveparkingguide.controllers.activities

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
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
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.AfterPermissionGranted

const val PERMISSIONS_REQUEST_FINE_LOCATION = 99

class MainActivity : AppCompatActivity() {

    private val hasLocationPermission
        get() = EasyPermissions.hasPermissions(this, Manifest.permission.ACCESS_FINE_LOCATION)

    private val parkingsRealtime:  ParkingsRealtime
            by lazy { ServiceProvider.provideParkingsRealTime() }

    private val fusedLocationClient: FusedLocationProviderClient
            by lazy { LocationServices.getFusedLocationProviderClient(this) }


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

        rv_parking.layoutManager    = LinearLayoutManager(baseContext)
        rv_parking.adapter          = adapter
        sr_parking.onRefresh { fetch() }
    }


    override fun onResume() {
        super.onResume()
        sr_parking.start    { fetch() }

    }


    private fun fetch() {
        subscribeToLocation()
        parkingsRealtime.parkings().enqueue(
                callback(onSuccess  = { adapter.setParkings(it) },
                         onEnd      = { sr_parking?.stop() }
                )
        )
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }


    @SuppressLint("MissingPermission")
    @AfterPermissionGranted(PERMISSIONS_REQUEST_FINE_LOCATION)
    private fun subscribeToLocation() {
        if (hasLocationPermission) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let { adapter.location = it }
            }
        } else {
            EasyPermissions.requestPermissions(
                    this, getString(R.string.location_permission_rationale),
                    PERMISSIONS_REQUEST_FINE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
            )
        }
    }


}
