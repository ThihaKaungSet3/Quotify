package non.shahad.quotify.datamodels

object models {
    data class QODResponse(var status: String,var quotes : List<Quotes>)

    data class Quotes(
        var quotes : String?,
        var author : String?,
        var tag :  List<String>?,
        var imgUrl : String? = "")
}