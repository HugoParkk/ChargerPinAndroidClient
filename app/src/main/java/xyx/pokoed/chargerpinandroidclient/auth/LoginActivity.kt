package xyx.pokoed.chargerpinandroidclient.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyx.pokoed.chargerpinandroidclient.App
import xyx.pokoed.chargerpinandroidclient.MainActivity
import xyx.pokoed.chargerpinandroidclient.R
import xyx.pokoed.chargerpinandroidclient.auth.data.LoginRequest
import xyx.pokoed.chargerpinandroidclient.auth.data.LoginResponse
import xyx.pokoed.chargerpinandroidclient.retrofit.RetrofitClient

class LoginActivity : AppCompatActivity() {
    private lateinit var inputId: EditText
    private lateinit var inputPwd: EditText
    private lateinit var btnLogin: Button
    private lateinit var toRegister: TextView
    private lateinit var autoLoginChk: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()
        inputId = findViewById(R.id.input_id)
        inputPwd = findViewById(R.id.input_pwd)
        btnLogin = findViewById(R.id.btn_login)
        toRegister = findViewById(R.id.to_register)
        autoLoginChk = findViewById(R.id.checkAutoLogin)

        val pref = getSharedPreferences("userInfo", MODE_PRIVATE)
        val id = pref.getString("userId", "")
        val pwd = pref.getString("userPassword", "")
        val callLogin = RetrofitClient.api.login(LoginRequest(id!!, pwd!!))
        Log.d("saasdf", id)
        Log.d("saasdf", pwd)
        callLogin.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                if (response.isSuccessful()) {
                    Toast.makeText(
                        applicationContext,
                        "${response.body()!!.user!!.userName} 입니다",
                        Toast.LENGTH_SHORT
                    ).show()

                    val mainIntent = Intent(applicationContext, MainActivity::class.java)
                    App.isLogined = true
                    App.userName = response.body()!!.user!!.userId

                    startActivity(mainIntent)
                    overridePendingTransition(0, 0)

                } else {
                    Toast.makeText(applicationContext, "실패 400", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "${t.message}", Toast.LENGTH_SHORT).show()
                Log.d("asdf", t.message.toString())
            }
        })

        btnLogin.setOnClickListener {
            val id = inputId.text.toString()
            val pwd = inputPwd.text.toString()
            val callLogin = RetrofitClient.api.login(LoginRequest(id, pwd))

            callLogin.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful()) {
                        Toast.makeText(
                            applicationContext,
                            "${response.body()!!.user!!.userName} 입니다",
                            Toast.LENGTH_SHORT
                        ).show()

                        if (autoLoginChk.isChecked) {
                            // id pwd 자동 로그인 때문에 저장
                            App.instance.saveUserInfoData(id, pwd)
                        }

                        val mainIntent = Intent(applicationContext, MainActivity::class.java)
                        App.isLogined = true
                        App.userName = response.body()!!.user!!.userId

                        startActivity(mainIntent)
                        overridePendingTransition(0, 0)
                    } else {
                        Toast.makeText(applicationContext, "실패 400", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
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