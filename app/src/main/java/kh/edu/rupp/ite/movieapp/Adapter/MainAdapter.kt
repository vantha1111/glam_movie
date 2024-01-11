package kh.edu.rupp.ite.movieapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kh.edu.rupp.ite.movieapp.Fragment.Home
import kh.edu.rupp.ite.movieapp.Model.MainModel
import kh.edu.rupp.ite.movieapp.Model.MovieModel
import kh.edu.rupp.ite.movieapp.R

class MainAdapter(private val context : Context, private val mainModel: List<MainModel>)
    : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

        class MainViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

            var movieTitle : TextView
            var itemRecycler: RecyclerView

            init {
                movieTitle =itemView.findViewById(R.id.tvGenreMovie)
                itemRecycler = itemView.findViewById(R.id.rvMovieChild)
            }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.parent_list, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mainModel.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.movieTitle.text = mainModel[position].movieTitle
        setMovItemRecycler(holder.itemRecycler, mainModel[position].movieModel)
    }

    private fun setMovItemRecycler(recyclerView: RecyclerView, movieModel: List<MovieModel>){
        val itemRecyclerAdapter = ListAdapter(context, movieModel)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = itemRecyclerAdapter
    }


}