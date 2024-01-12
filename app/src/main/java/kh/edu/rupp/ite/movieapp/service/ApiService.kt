package kh.edu.rupp.ite.movieapp.service

import kh.edu.rupp.ite.movieapp.mvc.model.LoginModel
import kh.edu.rupp.ite.movieapp.mvc.model.RegisterModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiServices {


    @POST("auth/register")
    fun register(@Body registerInfo: RegisterModel): Call<ResponseBody>

    @POST("auth/login")
    fun login(@Body loginInfo: LoginModel): Call<ResponseBody>

}