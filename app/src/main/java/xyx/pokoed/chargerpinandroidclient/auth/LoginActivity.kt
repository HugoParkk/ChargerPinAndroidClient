package xyx.pokoed.chargerpinandroidclient.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import xyx.pokoed.chargerpinandroidclient.R

class LoginActivity : AppCompatActivity() {
    lateinit var toRegister: TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        toRegister = findViewById(R.id.to_register)

        toRegister.setOnClickListener {
            val registerIntent = Intent(this, RegisterActivity::class.java)
            startActivity(registerIntent)
        }

    }
}