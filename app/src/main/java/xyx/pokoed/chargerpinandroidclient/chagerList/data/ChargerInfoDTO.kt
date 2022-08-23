package xyx.pokoed.chargerpinandroidclient.chagerList.data

data class ChargerInfoDTO(
    val response: Response,
)

data class Response(
    val header: Header,
    val body: Body,

)

data class Header(
    val resultCode: String,
    val resultMsg: String
)

data class Body(
    val items: Items,
    val numOfRows: Int,
    val pageNo: Int,
    val totalCount: Int
)

data class Items(
    val item: List<ChargerInfo>
)

data class ChargerInfo(
    val addr: String,
    val chargeTp: Int,
    val cpId: Int,
    val cpNm: String,
    val cpStat: Int,
    val cpTp: Int,
    val csId: Int,
    val csNm: String,
    val lat: Double,
    val longi: Double,
    val statUpdateDatetime: String
)