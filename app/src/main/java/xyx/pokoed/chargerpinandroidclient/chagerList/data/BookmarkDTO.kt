package xyx.pokoed.chargerpinandroidclient.chagerList.data

data class BookmarkDTO(
    val bookmarkId: Int?,
    val chargerName: String,
    val chargerAddr: String,
    val chargerId: Int,
    val userId: String
)

data class BookmarkResponse(
    val code: Int,
    val msg: String
)