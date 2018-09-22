package be.gent.liveparkingguide.controllers.adapters

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


    fun setParkings(newParkings : MutableList<Parking>) {
        newParkings.sortBy { it.description  }
        parkings.removeAll(parkings)
        parkings.addAll(newParkings)
        notifyDataSetChanged()
    }

}