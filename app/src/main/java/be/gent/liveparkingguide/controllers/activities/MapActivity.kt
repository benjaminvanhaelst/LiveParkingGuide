package be.gent.liveparkingguide.controllers.activities
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import be.gent.liveparkingguide.R
import be.gent.liveparkingguide.model.Parking
import kotlinx.android.synthetic.main.activity_map.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions


class MapActivity : AppCompatActivity() {

    companion object {
        const val PARKING_EXTRA = "parking"
    }

    val mapFragment : SupportMapFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        val parking = intent.getParcelableExtra<Parking>(PARKING_EXTRA)
        tv_name   .text = parking.description
        tv_address.text = parking.address
        tv_contact.text = parking.contactInfo

        mapFragment.getMapAsync{ map ->
            map.addMarker(MarkerOptions().position(parking.position))
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(parking.position, 15f))
        }

    }
}