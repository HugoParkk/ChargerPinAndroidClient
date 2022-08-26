package xyx.pokoed.chargerpinandroidclient.chagerList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyx.pokoed.chargerpinandroidclient.R
import xyx.pokoed.chargerpinandroidclient.chagerList.data.ChargerInfo
import xyx.pokoed.chargerpinandroidclient.chagerList.data.ChargerInfoDTO
import xyx.pokoed.chargerpinandroidclient.chagerList.data.ChargerRequest
import xyx.pokoed.chargerpinandroidclient.chagerList.data.Items
import xyx.pokoed.chargerpinandroidclient.retrofit.RetrofitClient

class ChagerListActivity : AppCompatActivity() {

    private lateinit var inputKeyword: EditText
    private lateinit var searchBtn: ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CustomAdapter
    private var dataSet: Array<ChargerInfo> = arrayOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chager_list)

        supportActionBar?.hide()

        inputKeyword = findViewById(R.id.search)
        searchBtn = findViewById(R.id.search_btn)
        recyclerView = findViewById(R.id.charger_list)

        initDogRecyclerView(recyclerView)

        searchBtn.setOnClickListener {
            Log.d("saf", inputKeyword.text.toString())
            val callLogin = RetrofitClient.api.search(ChargerRequest(inputKeyword.text.toString()))

            callLogin.enqueue(object : Callback<ChargerInfoDTO> {
                override fun onResponse(
                    call: Call<ChargerInfoDTO>,
                    response: Response<ChargerInfoDTO>
                ) {
                    if (response.isSuccessful()) {
                        val callLogin =
                            RetrofitClient.api.search(ChargerRequest(inputKeyword.text.toString()))
                        callLogin.enqueue(object : Callback<ChargerInfoDTO> {
                            override fun onResponse(
                                call: Call<ChargerInfoDTO>,
                                response: Response<ChargerInfoDTO>
                            ) {
                                if (response.isSuccessful()) {
                                    Log.d("saf", response.body()!!.response.body.items.toString())
                                    dataSet = response.body()!!.response.body.items.item
                                    Log.d("saaf", dataSet.get(0).toString())
                                    Toast.makeText(
                                        applicationContext,
                                        "${response.body()!!.response.body.items.item[0].csNm} ${response.body()!!.response.body.items.item[0].cpNm}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    adapter.notifyDataSetChanged()
                                } else {
                                    Toast.makeText(applicationContext, "실패 400", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }

                            override fun onFailure(call: Call<ChargerInfoDTO>, t: Throwable) {
                                Toast.makeText(
                                    applicationContext,
                                    "${t.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                                Log.d("asdf", t.message.toString())
                            }
                        })
                    }
                }

                override fun onFailure(call: Call<ChargerInfoDTO>, t: Throwable) {
                    Toast.makeText(applicationContext, "${t.message}", Toast.LENGTH_SHORT).show()
                    Log.d("asdf", t.message.toString())
                }
            })
        }
    }

    fun initDogRecyclerView(recyclerView: RecyclerView){
        adapter = CustomAdapter(dataSet = dataSet) //어댑터 객체 만듦
        recyclerView.adapter = adapter //리사이클러뷰에 어댑터 연결
        recyclerView.layoutManager = LinearLayoutManager(this) //레이아웃 매니저 연결
        recyclerView.setHasFixedSize(true)
    }
}