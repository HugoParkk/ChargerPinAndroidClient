package xyx.pokoed.chargerpinandroidclient

import android.app.Application

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

        lateinit var userName: String
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