package xyx.pokoed.chargerpinandroidclient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyx.pokoed.chargerpinandroidclient.auth.LoginActivity
import xyx.pokoed.chargerpinandroidclient.auth.data.LoginRequest
import xyx.pokoed.chargerpinandroidclient.auth.data.LoginResponse
import xyx.pokoed.chargerpinandroidclient.retrofit.RetrofitClient

class MainActivity : AppCompatActivity() {
    lateinit var homeLayout: LinearLayout
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        if (App.isLogined) {
            init()
            settingListener()
        } else {
            val loginIntent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(loginIntent)
        }

    }

    fun init() {
        homeLayout = findViewById(R.id.home_ly)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
    }

    fun settingListener() {
        bottomNavigationView.run {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.tab_home -> replaceFragment(HomeFragment())
                    R.id.tab_profile -> replaceFragment(ProfileFragment())
                    R.id.tab_setting -> replaceFragment(SettingFragment())
                }
                true
            }
            selectedItemId = R.id.tab_home
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(homeLayout.id, fragment).commit()
    }
}