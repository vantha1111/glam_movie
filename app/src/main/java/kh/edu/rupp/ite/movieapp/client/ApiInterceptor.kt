package kh.edu.rupp.ite.movieapp.client

import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        //Add Interceptor
        val request =
            chain.request().newBuilder().addHeader("Authorization", "Bearer Ykhang@18").build()
        return  chain.proceed(request)
    }
}