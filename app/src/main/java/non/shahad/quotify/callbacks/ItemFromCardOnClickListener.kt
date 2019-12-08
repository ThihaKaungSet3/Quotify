package non.shahad.quotify.callbacks

import com.yuyakaido.android.cardstackview.CardStackListener

interface ItemFromCardOnClickListener : CardStackListener {
    fun onColorViewClick()
    fun onFontViewClick()
    fun onShareClick()
    fun onAuthorClick()
}