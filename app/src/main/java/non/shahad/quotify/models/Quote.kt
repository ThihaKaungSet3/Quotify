package non.shahad.quotify.models

data class Quote(var quote : String,var author : String,var type : String){

    var title : String = quote
        get() = "‘ $quote ‘"

}
