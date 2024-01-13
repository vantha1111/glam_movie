package kh.edu.rupp.ite.movieapp.Activity

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kh.edu.rupp.ite.movieapp.R

class Detail : AppCompatActivity(R.layout.activity_detail) {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        val headingDetail : TextView = findViewById(R.id.movieNameTxt)
        val mainDetail: TextView = findViewById(R.id.textSummary)
        val imageDetail : ImageView = findViewById(R.id.imgDetail)

        val bundle : Bundle?= intent.extras
        val heading = bundle!!.getString("heading")
        val image = bundle.getInt("Imageid")
        val news = bundle.getString("news")

        headingDetail.text =heading
        mainDetail.text = news
        imageDetail.setImageResource(image)
    }

}