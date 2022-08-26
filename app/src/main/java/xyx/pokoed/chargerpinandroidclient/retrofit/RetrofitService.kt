package xyx.pokoed.chargerpinandroidclient.retrofit

import retrofit2.Call
import retrofit2.http.*
import xyx.pokoed.chargerpinandroidclient.auth.data.LoginRequest
import xyx.pokoed.chargerpinandroidclient.auth.data.LoginResponse
import xyx.pokoed.chargerpinandroidclient.auth.data.RegisterRequest
import xyx.pokoed.chargerpinandroidclient.auth.data.RegisterResponse
import xyx.pokoed.chargerpinandroidclient.chagerList.data.ChargerInfoDTO
import xyx.pokoed.chargerpinandroidclient.chagerList.data.ChargerRequest

interface RetrofitService {
    @POST("auth/login")
    fun login(@Body user: LoginRequest): Call<LoginResponse>

    @POST("auth/join")
    fun join(@Body user: RegisterRequest): Call<RegisterResponse>

    @POST("charger/info")
    fun search(@Body keyword: ChargerRequest): Call<ChargerInfoDTO>
}