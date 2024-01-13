package kh.edu.rupp.ite.movieapp.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kh.edu.rupp.ite.movieapp.Activity.Detail
import kh.edu.rupp.ite.movieapp.Adapter.SearchAdapter
import kh.edu.rupp.ite.movieapp.Model.SearchModel
import kh.edu.rupp.ite.movieapp.R
import kh.edu.rupp.ite.movieapp.databinding.FragmentSearchBinding
import java.util.*
import kotlin.collections.ArrayList


class Search : Fragment(R.layout.fragment_search), SearchAdapter.OnClickListener{

    private var searchRecycler:RecyclerView? = null
    private var searchAdapter: SearchAdapter? = null

    private var _binding: FragmentSearchBinding? =null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater,container,false)

        val searchModel: MutableList<SearchModel> = ArrayList()
            searchModel.add(SearchModel("image1", "image1 desc", R.drawable.god))
            searchModel.add(SearchModel("image2", "image2 desc", R.drawable.god))
            searchModel.add(SearchModel("image3", "image3 desc", R.drawable.god))
            searchModel.add(SearchModel("image4", "image4 desc", R.drawable.god))
            searchModel.add(SearchModel("image5", "image5 desc", R.drawable.god))
            searchModel.add(SearchModel("image6", "image6 desc", R.drawable.god))

        setSearchRecycler(searchModel)

        val searchView = binding.searchView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                val searchList = ArrayList<SearchModel>()

                if (newText != null){
                    for (i in searchModel){
                        if (i.name.lowercase(Locale.ROOT).contains(newText)){
                            searchList.add(i)
                        }
                    }
                    if (searchList.isEmpty()){
                        Toast.makeText(context,"NO Data",Toast.LENGTH_SHORT).show()}
                    else{

                        searchAdapter!!.onApplySearch(searchList)
                    }
                }
                return true
            }

        })




        return binding.root
    }

    private fun setSearchRecycler(searchModel: List<SearchModel>){
        searchRecycler = binding.searchList
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager (context )
        searchRecycler!!.layoutManager = layoutManager
        searchAdapter = SearchAdapter(requireContext(), searchModel, this)
        searchRecycler!!.adapter = searchAdapter
    }


    override fun ClickedItem(searchModel: SearchModel) {
        Log.e("TAG", searchModel.name );

        val intent = Intent(context, Detail::class.java)
        intent.putExtra("heading", searchModel.name )
        intent.putExtra("imageid", searchModel.desc)
        intent.putExtra("news",searchModel.image)
        startActivity(intent)
    }



}