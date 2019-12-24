package non.shahad.quotify.ui.home


import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.swipe_item.view.*
import non.shahad.quotify.R
import non.shahad.quotify.callbacks.ItemFromCardOnClickListener
import non.shahad.quotify.data.local.entities.ColorEntity
import non.shahad.quotify.datamodels.models
import non.shahad.quotify.ui.bottomsheets.choosefont.FontFamily
import non.shahad.quotify.ui.bottomsheets.choosefont.FontSize
import non.shahad.quotify.utils.ColorTool
import non.shahad.quotify.utils.FontCache

class SwipeCardAdapter(
    private val itemClick : ItemFromCardOnClickListener
) : RecyclerView.Adapter<SwipeCardAdapter.SwipeCardViewHolder>(), View.OnClickListener{

    private lateinit var selectedColor : ColorEntity
    private lateinit var fontFamily: FontFamily
    private lateinit var fontSize: FontSize
    private var quotesList: List<models.Quotes> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwipeCardViewHolder {

        val inflator = LayoutInflater.from(parent.context)

        return SwipeCardViewHolder(inflator.inflate(R.layout.swipe_item,parent,false))
    }

    override fun getItemCount(): Int = if (quotesList.size > -1) quotesList.size else throw ArrayIndexOutOfBoundsException()

    override fun onBindViewHolder(holder: SwipeCardViewHolder, position: Int) {
        val quote = quotesList[position]
        holder.bind(quote,selectedColor,fontFamily,fontSize)

        holder.itemView.setOnClickListener(this)
        holder.itemView.author.setOnClickListener(this)
        holder.itemView.changecolorview.setOnClickListener(this)
        holder.itemView.changefontBtn.setOnClickListener(this)
        holder.itemView.sharebtn.setOnClickListener(this)
    }

    fun setSelectedColor(selectedColor : ColorEntity){
        this.selectedColor = selectedColor
    }

    fun setFontFamily(fontFamily: FontFamily){
        this.fontFamily = fontFamily
    }

    fun setFontSize(fontSize: FontSize){
        this.fontSize = fontSize
    }


    fun setQuotesList(quotesList: List<models.Quotes>){
        this.quotesList = quotesList
        notifyDataSetChanged()
    }


    fun quotesList() = this.quotesList


    class SwipeCardViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bind(quote:models.Quotes,colorEntity: ColorEntity,fontFamily: FontFamily,fontSize: FontSize){
            itemView.quote.text = quote.quote
            itemView.author.text = quote.author
            changeColorViewBackground(ColorTool.gradientState(colorEntity.startColor,colorEntity.endColor,
                ColorTool.OVAL,0f))
            itemView.rootView.background = ColorTool.gradientState(colorEntity.startColor,colorEntity.endColor,
                ColorTool.RECTANGLE,30f)

            when(fontFamily){
                FontFamily.SOLWAY -> {itemView.quote.typeface = FontCache.get(R.font.solway,itemView.context)}
                FontFamily.CALISTOGA -> {itemView.quote.typeface = FontCache.get(R.font.calistoga,itemView.context)}
                FontFamily.ATALSI -> {itemView.quote.typeface = FontCache.get(R.font.alatsi,itemView.context)}
            }

            when(fontSize){
                FontSize.REGULAR -> changeFontSize(20f)
                FontSize.MEDIUM -> changeFontSize(24f)
                FontSize.LARGE -> changeFontSize(26f)
            }

        }

        private fun changeColorViewBackground(colorDrawable : GradientDrawable){
            colorDrawable.setStroke(2,android.graphics.Color.WHITE)
            itemView.changecolorview.background = colorDrawable
        }

        private fun changeFontSize(textSize : Float){
            itemView.quote.textSize = textSize
        }


    }

    override fun onClick(itemview : View?) {
        when(itemview?.id){
            R.id.author -> itemClick.onAuthorClick()
            R.id.changecolorview -> itemClick.onColorViewClick()
            R.id.changefontBtn -> itemClick.onFontViewClick()
            R.id.sharebtn -> itemClick.onShareClick()
        }
    }


}