package non.shahad.quotify.utils

import android.content.res.Resources

class Display {
    companion object{
        fun dpTopx (dp : Int): Int {
            return (dp * Resources.getSystem().displayMetrics.density) as Int
        }

        fun pxTodp(px : Int) : Int{
            return (px / Resources.getSystem().displayMetrics.density) as Int
        }
    }
}