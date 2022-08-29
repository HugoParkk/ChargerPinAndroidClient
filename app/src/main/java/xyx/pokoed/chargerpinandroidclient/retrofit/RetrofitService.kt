package xyx.pokoed.chargerpinandroidclient.retrofit

import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.Body
import xyx.pokoed.chargerpinandroidclient.auth.data.LoginRequest
import xyx.pokoed.chargerpinandroidclient.auth.data.LoginResponse
import xyx.pokoed.chargerpinandroidclient.auth.data.RegisterRequest
import xyx.pokoed.chargerpinandroidclient.auth.data.RegisterResponse
import xyx.pokoed.chargerpinandroidclient.chagerList.data.*

interface RetrofitService {
    @POST("auth/login")
    fun login(@Body user: LoginRequest): Call<LoginResponse>

    @POST("auth/join")
    fun join(@Body user: RegisterRequest): Call<RegisterResponse>

    @POST("charger/info")
    fun search(@Body keyword: ChargerRequest): Call<ChargerInfoDTO>

    @POST("charger/info")
    fun search1(@Body keyword: ChargerRequest): Call<ChargerInfoDTO1>

    @GET("bookmark/read/{userId}")
    fun getBookmark(@Path("userId") userId: String): Call<Array<BookmarkDTO>>

    @POST("bookmark/add")
    fun addBookmark(@Body bookmarkDTO: BookmarkDTO): Call<BookmarkResponse>

    @DELETE("bookmark/delete/{bookmarkId}/{userId}")
    fun deleteBookmark(@Path("bookmarkId") bookmarkId: Int, @Path("userId") userId: String): Call<BookmarkResponse>
}