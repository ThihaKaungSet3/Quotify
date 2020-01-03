package non.shahad.quotify.ui.explore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.explore_item.view.*
import non.shahad.quotify.R
import non.shahad.quotify.datamodels.models

class ExploreAdapter(
    val onclickListener : onClickListener
) : RecyclerView.Adapter<ExploreAdapter.ExploreViewHolder>() {
    private var topicList : List<models.Topics> = emptyList()

    class ExploreViewHolder(itemview : View) : RecyclerView.ViewHolder (itemview){
        fun bind(topic : models.Topics){
            itemView.topicName.text = topic.name

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreViewHolder {
        return ExploreViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.explore_item,parent,false))
    }

    override fun getItemCount(): Int = topicList.size

    override fun onBindViewHolder(holder: ExploreViewHolder, position: Int) {
        val topic = topicList[position]
        holder.bind(topic)

        holder.itemView.rootview.setOnClickListener{
            onclickListener.onClick(topic.name)
        }

    }

    fun setTopicList(topicList: List<models.Topics>){
        this.topicList = topicList
        notifyDataSetChanged()
    }

    interface onClickListener{
        open fun onClick(name : String?)
    }
}