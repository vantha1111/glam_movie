package kh.edu.rupp.ite.movieapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kh.edu.rupp.ite.movieapp.Model.SearchModel
import kh.edu.rupp.ite.movieapp.R

class SearchAdapter(private val context: Context, private val searchModel: List<SearchModel>, private val clickListener: OnClickListener) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.search_item,parent,false))
    }

    override fun getItemCount(): Int {
        return searchModel.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {

        val searchModel = searchModel[position]
        holder.name.text = searchModel.name
        holder.desc.text = searchModel.desc
        holder.image.setImageResource(searchModel.image)

        holder.itemView.setOnClickListener{
            clickListener.ClickedItem(searchModel)
        }
    }

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val name : TextView
        val desc :TextView
        val image : ImageView

        init {
            name = itemView.findViewById(R.id.tvName)
            desc =itemView.findViewById(R.id.tvDesc)
            image =itemView.findViewById(R.id.search_image)
        }


    }

    interface OnClickListener {
        fun ClickedItem(searchModel: SearchModel)
    }
}