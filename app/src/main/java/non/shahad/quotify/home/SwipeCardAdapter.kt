package non.shahad.quotify.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.swipe_item.view.*
import non.shahad.quotify.R
import non.shahad.quotify.callbacks.ItemFromCardOnClickListener
import non.shahad.quotify.models.Quote

class SwipeCardAdapter(
    private var quotesList : List<Quote>  = emptyList(),
    private val itemClick : ItemFromCardOnClickListener
) : RecyclerView.Adapter<SwipeCardAdapter.SwipeCardViewHolder>(), View.OnClickListener{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwipeCardViewHolder {

        val inflator = LayoutInflater.from(parent.context)

        return SwipeCardViewHolder(inflator.inflate(R.layout.swipe_item,parent,false))
    }

    override fun getItemCount(): Int = if (quotesList.size > -1) quotesList.size else throw ArrayIndexOutOfBoundsException()

    override fun onBindViewHolder(holder: SwipeCardViewHolder, position: Int) {
        val quote = quotesList[position]
        holder.bind(quote)

        holder.itemView.setOnClickListener(this)
        holder.itemView.author.setOnClickListener(this)
        holder.itemView.changecolorview.setOnClickListener(this)
        holder.itemView.changefontBtn.setOnClickListener(this)
        holder.itemView.sharebtn.setOnClickListener(this)
    }

    fun setQuotesList(quotesList: List<Quote>){
        this.quotesList = quotesList
    }

    fun quotesList() = this.quotesList


    class SwipeCardViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bind(quote: Quote){
            itemView.quote.text = quote.title
            itemView.author.text = quote.author
            when(quote.type){
                "motivation" -> itemView.rootView.background = itemView.resources.getDrawable(R.drawable.raspberry_blue)
                "jealousy" -> itemView.rootView.background = itemView.resources.getDrawable(R.drawable.socialive)
            }
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