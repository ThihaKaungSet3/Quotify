package non.shahad.quotify.bottomsheets

import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.choose_color_item.view.*
import non.shahad.quotify.R
import non.shahad.quotify.models.Color
import java.util.*

class ChooseColorAdapter(
    private var colorList : List<Color>
) : RecyclerView.Adapter<ChooseColorAdapter.ChooseColorViewHolder>() {


    class ChooseColorViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(color : Color){

            itemView.colorCircle.background = gradientState(color.startColor,color.endColor)

        }

        private fun gradientState(startColorHex : String, endColorHex : String) : GradientDrawable{
            // gradient Start and End color parse from Hex to Int
            val colorState = intArrayOf(parseHex(startColorHex),parseHex(endColorHex))
            val gradientShape = GradientDrawable(GradientDrawable.Orientation.TL_BR,colorState)
            gradientShape.shape = GradientDrawable.OVAL

            return gradientShape
        }

        private fun parseHex(hex : String) : Int =  android.graphics.Color.parseColor(hex)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseColorViewHolder {
        val view : View? = LayoutInflater.from(parent.context).inflate(R.layout.choose_color_item,parent,false)
        return ChooseColorViewHolder(view!!)
    }

    override fun getItemCount(): Int = if (colorList.size > -1) colorList.size else throw ArrayIndexOutOfBoundsException()

    override fun onBindViewHolder(holder: ChooseColorViewHolder, position: Int) {
        val color = colorList[position]
        holder.bind(color)
    }


}