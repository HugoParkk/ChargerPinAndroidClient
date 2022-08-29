package xyx.pokoed.chargerpinandroidclient

import android.app.Application
import android.content.Intent
import xyx.pokoed.chargerpinandroidclient.auth.LoginActivity
import xyx.pokoed.chargerpinandroidclient.chagerList.data.BookmarkDTO

class App: Application() {

    fun saveUserInfoData(id: String, pwd: String) {
        val pref = getSharedPreferences("userInfo", MODE_PRIVATE)
        val edit = pref.edit()
        edit.putString("userId", id)
        edit.putString("userPassword", pwd)
        edit.apply()
    }


    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()

    }

    companion object {
        lateinit var instance: App
        lateinit var bookmarkList: Array<BookmarkDTO>
        lateinit var userName: String
        var isLogined = false
//        fun saveData(userId: String) {
//            val pref = getSharedPreferences("userId", MODE_PRIVATE)
//            val edit = pref.edit()
//            edit.putString("Id", userId)
//            edit.apply()
//        }
    }
}