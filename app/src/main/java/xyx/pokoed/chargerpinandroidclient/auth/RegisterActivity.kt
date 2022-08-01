package xyx.pokoed.chargerpinandroidclient.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import xyx.pokoed.chargerpinandroidclient.R

class RegisterActivity : AppCompatActivity() {
    lateinit var toLogin: TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.hide()

        toLogin = findViewById(R.id.to_login)

        toLogin.setOnClickListener {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }
    }
}