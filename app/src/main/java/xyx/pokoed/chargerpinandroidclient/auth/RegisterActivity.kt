package xyx.pokoed.chargerpinandroidclient.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyx.pokoed.chargerpinandroidclient.R
import xyx.pokoed.chargerpinandroidclient.auth.data.LoginResponse
import xyx.pokoed.chargerpinandroidclient.auth.data.RegisterRequest
import xyx.pokoed.chargerpinandroidclient.auth.data.RegisterResponse
import xyx.pokoed.chargerpinandroidclient.retrofit.RetrofitClient

class RegisterActivity : AppCompatActivity() {
    lateinit var inputId: EditText;
    lateinit var inputPwd: EditText;
    lateinit var inputPwdRe: EditText;
    lateinit var inputName: EditText;
    lateinit var btnRegister: Button;
    lateinit var toLogin: TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.hide()

        inputId = findViewById(R.id.input_id)
        inputPwd = findViewById(R.id.input_pwd)
        inputPwdRe = findViewById(R.id.input_pwd_re)
        inputName = findViewById(R.id.input_name)
        btnRegister = findViewById(R.id.btn_register)
        toLogin = findViewById(R.id.to_login)

        btnRegister.setOnClickListener {
            val id = inputId.text.toString()
            val pwd = inputPwd.text.toString()
            val pwdRe = inputPwdRe.text.toString()
            val name = inputName.text.toString()

            if (!pwd.equals(pwdRe)) {
                Toast.makeText(applicationContext, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val callRegister = RetrofitClient.api.join(RegisterRequest(id, pwd, name))

            callRegister.enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    if (response.isSuccessful()) {
                        Toast.makeText(applicationContext, "${response.body()?.msg} 입니다", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(applicationContext, "실패 400", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, "${t.message}", Toast.LENGTH_SHORT).show()
                    Log.d("asdf", t.message.toString())
                }
            })

        }

        toLogin.setOnClickListener {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }
    }
}