package non.shahad.quotify.ui.bottomsheets.choosecolor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.choose_color_item.view.*
import non.shahad.quotify.R
import non.shahad.quotify.callbacks.BottomSheetItemChooseListener
import non.shahad.quotify.data.entities.ColorEntity
import non.shahad.quotify.utils.ColorTool
import non.shahad.quotify.utils.ColorTool.Companion.gradientState

class ChooseColorAdapter(
    private val colorChooseListener : BottomSheetItemChooseListener
) : RecyclerView.Adapter<ChooseColorAdapter.ChooseColorViewHolder>() {

    private var colorList : List<ColorEntity> = ArrayList()
    private var chooseColorId : Long = 1L

    class ChooseColorViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(color : ColorEntity, onChooseListener : BottomSheetItemChooseListener,chooseColorID: Long){

            val selectedBackground = gradientState(color.startColor,color.endColor,ColorTool.OVAL)

            itemView.colorCircle.background = selectedBackground

            if (color.id == chooseColorID) itemView.verifyIcon.visibility = View.VISIBLE

            itemView.colorCircle.setOnClickListener{
                onChooseListener.onColorChoose(color,adapterPosition)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseColorViewHolder {
        val view : View? = LayoutInflater.from(parent.context).inflate(R.layout.choose_color_item,parent,false)
        return ChooseColorViewHolder(
            view!!
        )
    }

    override fun getItemCount(): Int = if (colorList.size > -1) colorList.size else throw ArrayIndexOutOfBoundsException()

    override fun onBindViewHolder(holder: ChooseColorViewHolder, position: Int) {

        holder.bind(colorList[position],colorChooseListener,chooseColorId)

    }


    fun setChooseColor(chooseColorID : Long){
        this.chooseColorId = chooseColorID
    }

    fun setcolorList(colorList: List<ColorEntity>){
        this.colorList = colorList
        notifyDataSetChanged()
    }




}