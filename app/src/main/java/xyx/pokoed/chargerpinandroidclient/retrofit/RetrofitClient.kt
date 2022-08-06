package xyx.pokoed.chargerpinandroidclient.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://pokoed.xyz:8081/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val _api = retrofit.create(RetrofitService::class.java)
    val api get() = _api
}