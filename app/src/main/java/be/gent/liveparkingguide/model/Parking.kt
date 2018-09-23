package be.gent.liveparkingguide.model
import android.graphics.Color
import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlin.math.roundToInt


@Parcelize
data class Parking(
    @SerializedName("id")                       private val id              : Int,
    @SerializedName("lastModifiedDate")         private val lastModifiedDate: String,
    @SerializedName("name")                     private val name            : String,
    @SerializedName("description")              val description             : String,
    @SerializedName("latitude")                 private val latitude        : Double,
    @SerializedName("longitude")                private val longitude       : Double,
    @SerializedName("address")                  val address                 : String,
    @SerializedName("contactInfo")              val contactInfo             : String,
    @SerializedName("totalCapacity")            private val totalCapacity   : Int,
    @SerializedName("openingTimes")             val openingTimes            : List<OpeningTime>,
    @SerializedName("parkingStatus")            private val status          : ParkingStatus,
    @SerializedName("blurAvailability")         private val blurAvailability: Boolean
) : Parcelable {

    val position : LatLng
            get() = LatLng(latitude,longitude)

    val availableCapacity
            get() = status.availableCapacity

    private val usedCapacity : Int
            get() = with(status) { totalCapacity - availableCapacity }

    private fun Int.tocolor() : Int
            = (toFloat() / totalCapacity.toFloat() * 255).roundToInt()


    fun capacityColor() : Int = with(status) {
        val red     = usedCapacity      .tocolor()
        val green   = availableCapacity .tocolor()
        return Color.rgb(red, green, 0)
    }


}

@Parcelize
data class OpeningTime(
    @SerializedName("days")                     val days                    : List<Day>,
    @SerializedName("from")                     val from                    : String,
    @SerializedName("to")                       val to                      : String
) : Parcelable

@Parcelize
data class ParkingStatus(
    @SerializedName("availableCapacity")        val availableCapacity       : Int,
    @SerializedName("totalCapacity")            val totalCapacity           : Int,
    @SerializedName("open")                     val open                    : Boolean,
    @SerializedName("lastModifiedDate")         val lastModifiedDate        : String
) : Parcelable

enum class Day {
    @SerializedName("MONDAY")       MONDAY,
    @SerializedName("TUESDAY")      TUESDAY,
    @SerializedName("WEDNESDAY")    WEDNESDAY,
    @SerializedName("THURSDAY")     THURSDAY,
    @SerializedName("FRIDAY")       FRIDAY,
    @SerializedName("SATURDAY")     SATURDAY,
    @SerializedName("SUNDAY")       SUNDAY
}