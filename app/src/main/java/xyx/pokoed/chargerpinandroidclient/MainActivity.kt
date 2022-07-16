package xyx.pokoed.chargerpinandroidclient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import xyx.pokoed.chargerpinandroidclient.auth.LoginActivity

class MainActivity : AppCompatActivity() {
    lateinit var loginBtn: Button;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginBtn = findViewById(R.id.btn_login)


        loginBtn.setOnClickListener {
            val loginIntent: Intent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }

    }
}