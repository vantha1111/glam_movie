package kh.edu.rupp.ite.movieapp.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kh.edu.rupp.ite.movieapp.Adapter.SearchAdapter
import kh.edu.rupp.ite.movieapp.Model.SearchModel
import kh.edu.rupp.ite.movieapp.R
import kh.edu.rupp.ite.movieapp.databinding.FragmentSearchBinding


class Search : Fragment(R.layout.fragment_search), SearchAdapter.OnClickListener {

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
        Log.e("TAG", searchModel.name )
    }

}