package kh.edu.rupp.ite.movieapp.client

import kh.edu.rupp.ite.movieapp.service.ApiServices
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient private constructor() {
    private val okHttpClient = OkHttpClient.Builder().addInterceptor(ApiInterceptor()).build()

    // create Singleton
    private val httpClient = Retrofit.Builder()
        .baseUrl("http://192.168.100.13:8000/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
    //It's Obj static

    val apiServices = httpClient.create(ApiServices::class.java)

    companion object {
        private var instance: ApiClient? = null
        fun get(): ApiClient {
            if (instance == null) {
                instance = ApiClient()
            }
            return instance!!

        }
    }

}