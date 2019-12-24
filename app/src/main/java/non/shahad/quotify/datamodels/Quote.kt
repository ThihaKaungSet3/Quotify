package non.shahad.quotify.datamodels

data class Quote(var quote : String,var author : String,var type : String = ""){

    var title : String = quote
        get() = "‘ $quote ‘"

}
