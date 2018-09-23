package be.gent.liveparkingguide.controllers.viewHolders

import android.support.v7.widget.RecyclerView
import android.view.View
import be.gent.liveparkingguide.model.Parking
import kotlinx.android.synthetic.main.holder_parking.view.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.sdk27.coroutines.onClick

/**
 * Created by bvanhaelst
 */
class ParkingHolder( val view: View ) : RecyclerView.ViewHolder(view) {

    fun bind(parking: Parking, onItemClick: (parking : Parking)-> Unit) = with (itemView) {

        tv_name.text        = parking.description
        tv_free_places.text = "${parking.availableCapacity}"
        tv_free_places.backgroundColor = parking.capacityColor()

        ll_holder.onClick { onItemClick(parking) }

    }
}