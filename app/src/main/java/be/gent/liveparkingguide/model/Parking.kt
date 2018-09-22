package be.gent.liveparkingguide.model
import com.google.gson.annotations.SerializedName




data class Parking(
    @SerializedName("id")                       val id                      : Int,
    @SerializedName("lastModifiedDate")         val lastModifiedDate        : String,
    @SerializedName("name")                     val name                    : String,
    @SerializedName("description")              val description             : String,
    @SerializedName("latitude")                 val latitude                : Double,
    @SerializedName("longitude")                val longitude               : Double,
    @SerializedName("address")                  val address                 : String,
    @SerializedName("contactInfo")              val contactInfo             : String,
//  @SerializedName("city")                           val city                    : City,
//  @SerializedName("parkingServer")                  val parkingServer           : ParkingServer,
//  @SerializedName("suggestedFreeThreshold")         val suggestedFreeThreshold  : Int,
//  @SerializedName("suggestedFullThreshold")         val suggestedFullThreshold  : Int,
//  @SerializedName("capacityRounding")               val capacityRounding        : Int,
    @SerializedName("totalCapacity")            val totalCapacity           : Int,
    @SerializedName("openingTimes")             val openingTimes            : List<OpeningTime>,
    @SerializedName("parkingStatus")            val parkingStatus           : ParkingStatus,
    @SerializedName("blurAvailability")         val blurAvailability        : Boolean
)

data class OpeningTime(
    @SerializedName("days")                     val days                    : List<Day>,
    @SerializedName("from")                     val from                    : String,
    @SerializedName("to")                       val to                      : String
)

data class ParkingStatus(
    @SerializedName("availableCapacity")        val availableCapacity       : Int,
    @SerializedName("totalCapacity")            val totalCapacity           : Int,
    @SerializedName("open")                     val open                    : Boolean,
//  @SerializedName("suggestedCapacity")              val suggestedCapacity       : String,
//  @SerializedName("activeRoute")                    val activeRoute             : String,
    @SerializedName("lastModifiedDate")         val lastModifiedDate        : String
)

enum class Day {
    @SerializedName("MONDAY")       MONDAY,
    @SerializedName("TUESDAY")      TUESDAY,
    @SerializedName("WEDNESDAY")    WEDNESDAY,
    @SerializedName("THURSDAY")     THURSDAY,
    @SerializedName("FRIDAY")       FRIDAY,
    @SerializedName("SATURDAY")     SATURDAY,
    @SerializedName("SUNDAY")       SUNDAY
}