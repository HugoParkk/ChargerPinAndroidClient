package xyx.pokoed.chargerpinandroidclient.retrofit

import retrofit2.Call
import retrofit2.http.*
import xyx.pokoed.chargerpinandroidclient.auth.data.AuthRequest
import xyx.pokoed.chargerpinandroidclient.auth.data.AuthResponse

interface RetrofitService {
    @POST("auth/login")
    fun login(@Body user: AuthRequest): Call<AuthResponse>
}