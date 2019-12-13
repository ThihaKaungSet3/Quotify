package non.shahad.quotify.utils

import android.graphics.drawable.GradientDrawable

class ColorTool {
    companion object{
        const val RECTANGLE = GradientDrawable.RECTANGLE
        const val OVAL = GradientDrawable.OVAL

        fun gradientState(startColorHex : String, endColorHex : String,shape : Int,radius : Float = 0f) : GradientDrawable {
            // gradient Start and End color parse from Hex to Int
            val colorState = intArrayOf(parseHex(startColorHex),parseHex(endColorHex))
            val gradientShape = GradientDrawable(GradientDrawable.Orientation.TL_BR,colorState)
            gradientShape.cornerRadius = radius

            if (shape == RECTANGLE){
                gradientShape.shape = GradientDrawable.RECTANGLE
            }else gradientShape.shape = GradientDrawable.OVAL


            return gradientShape
        }

        private fun parseHex(hex : String) : Int =  android.graphics.Color.parseColor(hex)

    }
}