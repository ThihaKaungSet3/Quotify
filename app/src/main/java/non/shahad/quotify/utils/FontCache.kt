package non.shahad.quotify.utils

import android.content.Context
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat
import java.util.*

class FontCache {
    companion object{
        private val fontCache = Hashtable<Int,Typeface>()

        fun get(resId : Int,context : Context) : Typeface? {
            var tf = fontCache[resId]

            tf = ResourcesCompat.getFont(context,resId)

            fontCache[resId] = tf

            return tf
        }
    }
}