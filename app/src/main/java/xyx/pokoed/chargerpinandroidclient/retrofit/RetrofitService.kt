package xyx.pokoed.chargerpinandroidclient.retrofit

import retrofit2.Call
import retrofit2.http.*
import xyx.pokoed.chargerpinandroidclient.auth.data.LoginRequest
import xyx.pokoed.chargerpinandroidclient.auth.data.LoginResponse

interface RetrofitService {
    @POST("auth/login")
    fun login(@Body user: LoginRequest): Call<LoginResponse>
}