package xyx.pokoed.chargerpinandroidclient.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import xyx.pokoed.chargerpinandroidclient.R
import xyx.pokoed.chargerpinandroidclient.auth.data.AuthRequest
import xyx.pokoed.chargerpinandroidclient.auth.data.AuthResponse
import xyx.pokoed.chargerpinandroidclient.retrofit.RetrofitClient
import xyx.pokoed.chargerpinandroidclient.retrofit.RetrofitService

class LoginActivity : AppCompatActivity() {
    lateinit var inputId: EditText
    lateinit var inputPwd: EditText
    lateinit var btnLogin: Button
    lateinit var toRegister: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()
        inputId = findViewById(R.id.input_id)
        inputPwd = findViewById(R.id.input_pwd)
        btnLogin = findViewById(R.id.btn_login)
        toRegister = findViewById(R.id.to_register)

        btnLogin.setOnClickListener {
            val id = inputId.text.toString()
            val pwd = inputPwd.text.toString()
            val callLogin = RetrofitClient.api.login(AuthRequest(id, pwd))

            callLogin.enqueue(object : Callback<AuthResponse> {
                override fun onResponse(
                    call: Call<AuthResponse>,
                    response: Response<AuthResponse>
                ) {
                    if (response.isSuccessful()) {
                        Toast.makeText(applicationContext, "${response.body()?.msg} 입니다", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(applicationContext, "실패 400", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, "${t.message}", Toast.LENGTH_SHORT).show()
                    Log.d("asdf", t.message.toString())
                }
            })
        }

        toRegister.setOnClickListener {
            val registerIntent = Intent(this, RegisterActivity::class.java)
            startActivity(registerIntent)
        }

    }
}