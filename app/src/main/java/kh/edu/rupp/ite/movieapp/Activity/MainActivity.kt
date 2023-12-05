package kh.edu.rupp.ite.movieapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kh.edu.rupp.ite.movieapp.Fragment.Home
import kh.edu.rupp.ite.movieapp.Fragment.Profile
import kh.edu.rupp.ite.movieapp.Fragment.Search
import kh.edu.rupp.ite.movieapp.R
import kh.edu.rupp.ite.movieapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Home())

        binding.btnNav.setOnItemSelectedListener {

            when(it.itemId){

                R.id.home -> replaceFragment(Home())
                R.id.profile -> replaceFragment(Profile())
                R.id.search -> replaceFragment(Search())

                else -> {

                }

            }

            true

        }


    }

    private fun replaceFragment(fragment: Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.frame_layout,fragment)
        fragmentTransition.commit()
    }
}