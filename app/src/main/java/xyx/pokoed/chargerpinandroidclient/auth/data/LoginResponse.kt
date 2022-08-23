package xyx.pokoed.chargerpinandroidclient.auth.data

data class LoginResponse (
    val code: Int,
    val msg: String,
    val user: UserInfoDTO?
)