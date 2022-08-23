package xyx.pokoed.chargerpinandroidclient.auth.data

data class RegisterResponse (
    val code: Int,
    val msg: String,
    val user: UserInfoDTO?
)