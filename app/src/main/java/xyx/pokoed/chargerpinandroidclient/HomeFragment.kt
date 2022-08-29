package xyx.pokoed.chargerpinandroidclient

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyx.pokoed.chargerpinandroidclient.chagerList.CustomAdapter
import xyx.pokoed.chargerpinandroidclient.chagerList.data.*
import xyx.pokoed.chargerpinandroidclient.retrofit.RetrofitClient

class HomeFragment : Fragment() {

    private lateinit var inputKeyword: EditText
    private lateinit var searchBtn: ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CustomAdapter
    private lateinit var id: String
    private var dataSet: Array<ChargerInfo> = arrayOf()
    var isSearched: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.fragment_home, container, false)

        inputKeyword = v.findViewById(R.id.search)
        searchBtn = v.findViewById(R.id.search_btn)
        recyclerView = v.findViewById(R.id.charger_list)


        // 북마크 로딩
        id = App.userName

        val callList = RetrofitClient.api.getBookmark(id!!)
        callList.enqueue(object : Callback<Array<BookmarkDTO>> {
            override fun onResponse(
                call: Call<Array<BookmarkDTO>>,
                response: Response<Array<BookmarkDTO>>
            ) {
                Log.d("aasssa", response.body()!!.size.toString())
                App.bookmarkList = response.body()!!
                if (response.body()!!.size > 0) {
                    dataSet.toMutableList()

                    for (i: Int in 0..response.body()!!.size - 1) {
                        val callList = RetrofitClient.api.search(
                            ChargerRequest(
                                response.body()!!.get(i).chargerAddr
                            )
                        )

                        callList.enqueue(object : Callback<ChargerInfoDTO> {
                            override fun onResponse(
                                call: Call<ChargerInfoDTO>,
                                response: Response<ChargerInfoDTO>
                            ) {
                                if (response.isSuccessful()) {
                                    dataSet =
                                        append(dataSet, response.body()!!.response.body.items.item)

                                    initListRecyclerView(recyclerView)
                                } else {
                                    Toast.makeText(context, "실패 400", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }

                            override fun onFailure(call: Call<ChargerInfoDTO>, t: Throwable) {
                                Toast.makeText(
                                    context,
                                    "${t.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                                val callList = RetrofitClient.api.search1(
                                    ChargerRequest(
                                        response.body()!!.get(i).chargerAddr
                                    )
                                )

                                callList.enqueue(object : Callback<ChargerInfoDTO1> {
                                    override fun onResponse(
                                        call: Call<ChargerInfoDTO1>,
                                        response: Response<ChargerInfoDTO1>
                                    ) {
                                        if (response.isSuccessful()) {
                                            dataSet =
                                                append(
                                                    dataSet,
                                                    response.body()!!.response.body.items.item
                                                )

                                            initListRecyclerView(recyclerView)
                                        } else {
                                            Toast.makeText(context, "실패 400", Toast.LENGTH_SHORT)
                                                .show()
                                        }
                                    }

                                    override fun onFailure(
                                        call: Call<ChargerInfoDTO1>,
                                        t: Throwable
                                    ) {
                                        Toast.makeText(
                                            context,
                                            "${t.message}",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        callList
                                        Log.d("asdf2", t.message.toString())
                                    }
                                })
                            }
                        })
                    }
                }
            }


            override fun onFailure(call: Call<Array<BookmarkDTO>>, t: Throwable) {
                Toast.makeText(
                    context,
                    "${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d("asdf2", t.message.toString())
            }
        })

        // 검색 부분
        searchBtn.setOnClickListener {
            Log.d("saf", inputKeyword.text.toString())
            isSearched = true
            val callList =
                RetrofitClient.api.search(ChargerRequest(inputKeyword.text.toString()))

            callList.enqueue(object : Callback<ChargerInfoDTO> {
                override fun onResponse(
                    call: Call<ChargerInfoDTO>,
                    response: Response<ChargerInfoDTO>
                ) {
                    if (response.isSuccessful()) {
                        Log.d("saf", response.body()!!.response.body.items.toString())
                        dataSet = response.body()!!.response.body.items.item
                        Log.d("saaf", dataSet.get(0).toString())
                        Toast.makeText(
                            context,
                            "${response.body()!!.response.body.items.item[0].csNm} ${response.body()!!.response.body.items.item[0].cpNm}",
                            Toast.LENGTH_SHORT
                        ).show()
                        initListRecyclerView(recyclerView)
                    } else {
                        Toast.makeText(context, "실패 400", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                override fun onFailure(call: Call<ChargerInfoDTO>, t: Throwable) {
                    Toast.makeText(context, "${t.message}", Toast.LENGTH_SHORT).show()
                    Log.d("asdf", t.message.toString())
                    val callList =
                        RetrofitClient.api.search(ChargerRequest(inputKeyword.text.toString()))
                    callList.enqueue(object : Callback<ChargerInfoDTO> {
                        override fun onResponse(
                            call: Call<ChargerInfoDTO>,
                            response: Response<ChargerInfoDTO>
                        ) {
                            if (response.isSuccessful()) {
                                Log.d(
                                    "saf",
                                    response.body()!!.response.body.items.toString()
                                )
                                dataSet = response.body()!!.response.body.items.item
                                Log.d("saaf", dataSet.get(0).toString())
                                Toast.makeText(
                                    context,
                                    "${response.body()!!.response.body.items.item[0].csNm} ${response.body()!!.response.body.items.item[0].cpNm}",
                                    Toast.LENGTH_SHORT
                                ).show()
                                initListRecyclerView(recyclerView)
                            } else {
                                Toast.makeText(context, "실패 400", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }

                        override fun onFailure(call: Call<ChargerInfoDTO>, t: Throwable) {
                            Toast.makeText(
                                context,
                                "${t.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                            Log.d("asdf2", t.message.toString())
                        }
                    })
                }
            })
        }



        return v
    }

    fun append(arr: Array<ChargerInfo>, element: Array<ChargerInfo>): Array<ChargerInfo> {
        val list: MutableList<ChargerInfo> = arr.toMutableList()
        element.iterator().forEach {
            list.add(it)
        }
        return list.toTypedArray()
    }

    fun append(arr: Array<ChargerInfo>, element: ChargerInfo): Array<ChargerInfo> {
        val list: MutableList<ChargerInfo> = arr.toMutableList()
        list.add(element)

        return list.toTypedArray()
    }

    fun initListRecyclerView(recyclerView: RecyclerView) {
        adapter = CustomAdapter(dataSet = dataSet, isSearched = isSearched, id, this) //어댑터 객체 만듦
        recyclerView.adapter = adapter //리사이클러뷰에 어댑터 연결
        recyclerView.layoutManager = LinearLayoutManager(context) //레이아웃 매니저 연결
        recyclerView.setHasFixedSize(true)
        isSearched = false
    }
}