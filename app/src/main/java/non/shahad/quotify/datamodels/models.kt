package non.shahad.quotify.datamodels

import com.google.gson.annotations.SerializedName

object models {
    data class QODResponse(
        @SerializedName("status")var status: String,
        @SerializedName("quotes")var quotes : List                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  <Quotes>
    )

    data class Quotes(
        var quote : String?,
        var author : String?,
        var tags : List<String>?)

    data class Topics(
        var name : String?,
        var resource : String? = ""
    )



}