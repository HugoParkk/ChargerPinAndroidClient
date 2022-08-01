package xyx.pokoed.chargerpinandroidclient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import xyx.pokoed.chargerpinandroidclient.auth.LoginActivity

class MainActivity : AppCompatActivity() {
    lateinit var toLoginBtn: Button;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toLoginBtn = findViewById(R.id.btn_to_login)


        toLoginBtn.setOnClickListener {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }

    }
}