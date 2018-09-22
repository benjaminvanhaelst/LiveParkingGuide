package be.gent.liveparkingguide.controllers.viewHolders

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import be.gent.liveparkingguide.model.Parking
import kotlinx.android.synthetic.main.holder_parking.view.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.sdk27.coroutines.onClick
import kotlin.math.roundToInt

/**
 * Created by bvanhaelst
 */
class ParkingHolder( val view: View ) : RecyclerView.ViewHolder(view) {

    fun bind(parking: Parking, onItemClick: (parking : Parking)-> Unit) = with (itemView) {


        tv_name.text        = parking.description
        tv_free_places.text = "${parking.status.availableCapacity}"

        val green   = ((parking.status.availableCapacity.toFloat()/parking.totalCapacity.toFloat()) * 255).roundToInt()
        val red     = (((parking.totalCapacity - parking.status.availableCapacity).toFloat() / parking.totalCapacity.toFloat()) * 255).roundToInt()
        tv_free_places.backgroundColor = Color.rgb( red, green, 0)

        ll_holder.onClick { onItemClick(parking) }

    }
}