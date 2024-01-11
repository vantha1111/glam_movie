package kh.edu.rupp.ite.movieapp.Fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kh.edu.rupp.ite.movieapp.Adapter.MainAdapter
import kh.edu.rupp.ite.movieapp.Adapter.SliderAdapter
import kh.edu.rupp.ite.movieapp.Model.MainModel
import kh.edu.rupp.ite.movieapp.Model.MovieModel
import kh.edu.rupp.ite.movieapp.R
import kh.edu.rupp.ite.movieapp.databinding.FragmentHomeBinding
import org.w3c.dom.Text
import kotlin.math.abs

class Home : Fragment(R.layout.fragment_home) {


    private lateinit var viewPager2: ViewPager2
    private lateinit var handler: Handler
    private lateinit var imageList:ArrayList<Int>
    private lateinit var adapter: SliderAdapter

    private var mainMovieRecycler:RecyclerView? = null
    private var mainAdapter: MainAdapter? = null


    private var _binding: FragmentHomeBinding? =null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater , container, false)

        init()
        setUpTransformer()

        viewPager2.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable , 2000)
            }
        })



        val movieModel1: MutableList<MovieModel> = ArrayList()
        movieModel1.add(MovieModel(1,R.drawable.jum))
        movieModel1.add(MovieModel(1,R.drawable.jum))
        movieModel1.add(MovieModel(1,R.drawable.jum))
        movieModel1.add(MovieModel(1,R.drawable.jum))
        movieModel1.add(MovieModel(1,R.drawable.jum))
        movieModel1.add(MovieModel(1,R.drawable.jum))
        movieModel1.add(MovieModel(1,R.drawable.jum))

        val movieModel2: MutableList<MovieModel> = ArrayList()
        movieModel2.add(MovieModel(1,R.drawable.jum))
        movieModel2.add(MovieModel(1,R.drawable.jum))
        movieModel2.add(MovieModel(1,R.drawable.jum))
        movieModel2.add(MovieModel(1,R.drawable.jum))
        movieModel2.add(MovieModel(1,R.drawable.jum))
        movieModel2.add(MovieModel(1,R.drawable.jum))
        movieModel2.add(MovieModel(1,R.drawable.jum))

        val movieModel3: MutableList<MovieModel> = ArrayList()
        movieModel3.add(MovieModel(1,R.drawable.jum))
        movieModel3.add(MovieModel(1,R.drawable.jum))
        movieModel3.add(MovieModel(1,R.drawable.jum))
        movieModel3.add(MovieModel(1,R.drawable.jum))
        movieModel3.add(MovieModel(1,R.drawable.jum))
        movieModel3.add(MovieModel(1,R.drawable.jum))
        movieModel3.add(MovieModel(1,R.drawable.jum))

        val movieModel4: MutableList<MovieModel> = ArrayList()
        movieModel4.add(MovieModel(1,R.drawable.jum))
        movieModel4.add(MovieModel(1,R.drawable.jum))
        movieModel4.add(MovieModel(1,R.drawable.jum))
        movieModel4.add(MovieModel(1,R.drawable.jum))
        movieModel4.add(MovieModel(1,R.drawable.jum))
        movieModel4.add(MovieModel(1,R.drawable.jum))
        movieModel4.add(MovieModel(1,R.drawable.jum))



        val mainModel: MutableList<MainModel> = ArrayList()
        mainModel.add(MainModel("New Releases", movieModel1))
        mainModel.add(MainModel("Popular movies", movieModel2))
        mainModel.add(MainModel("Coming Soon", movieModel3))
        mainModel.add(MainModel("Hollywood", movieModel4))

        setMainMovieRecycler(mainModel)


        return binding.root
    }


    override fun onPause() {
        super.onPause()

        handler.removeCallbacks(runnable)
    }
    override fun onResume() {
        super.onResume()

        handler.postDelayed(runnable, 2000)
    }


    private val runnable  = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }

    private fun setUpTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }
        viewPager2.setPageTransformer(transformer)
    }

    private fun init(){
        viewPager2 = binding.viewPager2
        handler = Handler(Looper.myLooper()!!)
        imageList = ArrayList()

        imageList.add(R.drawable.one)
        imageList.add(R.drawable.god)
        imageList.add(R.drawable.jum)
        imageList.add(R.drawable.time)

        adapter = SliderAdapter(imageList, viewPager2)

        viewPager2.adapter = adapter
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding =false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }

    private fun setMainMovieRecycler(mainModel: List<MainModel>){
        mainMovieRecycler = binding.MovieImg
        val layoutManager:RecyclerView.LayoutManager = LinearLayoutManager (context )
        mainMovieRecycler!!.layoutManager = layoutManager
        mainAdapter = MainAdapter(requireContext(), mainModel)
        mainMovieRecycler!!.adapter = mainAdapter
    }


}