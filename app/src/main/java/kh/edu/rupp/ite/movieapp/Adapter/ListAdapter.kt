package kh.edu.rupp.ite.movieapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.R
import androidx.recyclerview.widget.RecyclerView
import kh.edu.rupp.ite.movieapp.Fragment.Home
import kh.edu.rupp.ite.movieapp.Model.MovieModel

class ListAdapter (private val context: Context, private val movieModel: List<MovieModel>) :
    RecyclerView.Adapter<ListAdapter.ListItemViewHolder>() {

    class ListItemViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

        var itemImage: ImageView
        init {
            itemImage = itemView.findViewById(kh.edu.rupp.ite.movieapp.R.id.item_image)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(kh.edu.rupp.ite.movieapp.R.layout.movie_list, parent, false)
        return ListAdapter.ListItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieModel.size
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.itemImage.setImageResource(movieModel[position].imageURL)
    }

}
