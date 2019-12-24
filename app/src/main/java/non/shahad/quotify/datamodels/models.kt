package non.shahad.quotify.datamodels

import com.google.gson.annotations.SerializedName

object models {
    data class QODResponse(
        @SerializedName("status")var status: String,
        @SerializedName("quotes")var quotes : List                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  <Quotes>
    )

    data class Quotes(
        @SerializedName("quote")var quote : String?,
        @SerializedName("author")var author : String?)
}