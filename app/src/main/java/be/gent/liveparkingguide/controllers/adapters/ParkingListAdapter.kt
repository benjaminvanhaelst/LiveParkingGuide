package be.gent.liveparkingguide.controllers.adapters

import android.location.Location
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import be.gent.liveparkingguide.R
import be.gent.liveparkingguide.controllers.viewHolders.ParkingHolder
import be.gent.liveparkingguide.extensions.inflate
import be.gent.liveparkingguide.model.Parking

/**
 * Created by bvanhaelst
 */
class ParkingListAdapter( private val parkings : MutableList<Parking> = mutableListOf(), val onItemClick : (parking : Parking) -> Unit ) : RecyclerView.Adapter<ParkingHolder>() {

    override fun getItemCount()
            = parkings.size

    override fun onBindViewHolder(holder: ParkingHolder, position: Int)
            = holder.bind(parkings[position], onItemClick )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = ParkingHolder(parent.inflate(R.layout.holder_parking))

    var location : Location? = null
    set(value) {
        field = value
        updateUi()
    }


    fun setParkings(newParkings : MutableList<Parking>) {
        parkings.removeAll(parkings)
        parkings.addAll(newParkings)
        updateUi()
    }


    fun updateUi() {
        val currentLocation = location
        if (currentLocation != null) {
            parkings.sortBy {
                val result = FloatArray(1)
                Location.distanceBetween(
                        currentLocation.latitude,
                        currentLocation.longitude,
                        it.latitude,
                        it.longitude,
                        result)

                result[0]
            }
        } else {
            parkings.sortBy { it.description }
        }
        notifyDataSetChanged()
    }



}