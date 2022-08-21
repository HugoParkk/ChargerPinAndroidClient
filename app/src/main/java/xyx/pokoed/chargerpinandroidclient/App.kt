package xyx.pokoed.chargerpinandroidclient

import android.app.Application
import android.content.Context

class App: Application() {

    fun saveUserInfoData(userInfo: String) {
        val pref = getSharedPreferences("userInfo", MODE_PRIVATE)
        val edit = pref.edit()
        edit.putString("info", userInfo)
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
//        fun saveData(userId: String) {
//            val pref = getSharedPreferences("userId", MODE_PRIVATE)
//            val edit = pref.edit()
//            edit.putString("Id", userId)
//            edit.apply()
//        }
    }
}