package xyx.pokoed.chargerpinandroidclient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import xyx.pokoed.chargerpinandroidclient.auth.LoginActivity
import xyx.pokoed.chargerpinandroidclient.auth.RegisterActivity
import xyx.pokoed.chargerpinandroidclient.chagerList.ChagerListActivity

class MainActivity : AppCompatActivity() {
    lateinit var toLoginBtn: Button;
    lateinit var toRegisterBtn: Button;
    lateinit var toChargerListBtn: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toLoginBtn = findViewById(R.id.btn_to_login)
        toRegisterBtn = findViewById(R.id.btn_to_register)
        toChargerListBtn = findViewById(R.id.btn_to_charger_list)


        toLoginBtn.setOnClickListener {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }
        toRegisterBtn.setOnClickListener {
            val registerIntent = Intent(this, RegisterActivity::class.java)
            startActivity(registerIntent)
        }
        toChargerListBtn.setOnClickListener {
            val chagerListIntent = Intent(this, ChagerListActivity::class.java)
            startActivity(chagerListIntent)
        }


    }
}