package be.gent.liveparkingguide.controllers.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import be.gent.liveparkingguide.R
import be.gent.liveparkingguide.model.Parking
import kotlinx.android.synthetic.main.activity_map.*

/**
 * Created by bvanhaelst
 */
class MapActivity : AppCompatActivity() {

    companion object {
        const val PARKING_EXTRA = "parking"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val parking = intent.getParcelableExtra<Parking>(PARKING_EXTRA)

        tv_name   .text = parking.description
        tv_address.text = parking.address
        tv_contact.text = parking.contactInfo

    }
}