package xyx.pokoed.chargerpinandroidclient.chagerList

import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyx.pokoed.chargerpinandroidclient.App
import xyx.pokoed.chargerpinandroidclient.HomeFragment
import xyx.pokoed.chargerpinandroidclient.R
import xyx.pokoed.chargerpinandroidclient.chagerList.data.Body
import xyx.pokoed.chargerpinandroidclient.chagerList.data.BookmarkDTO
import xyx.pokoed.chargerpinandroidclient.chagerList.data.BookmarkResponse
import xyx.pokoed.chargerpinandroidclient.chagerList.data.ChargerInfo
import xyx.pokoed.chargerpinandroidclient.retrofit.RetrofitClient

class CustomAdapter(
    private val dataSet: Array<ChargerInfo>,
    private val isSearched: Boolean,
    private val id: String,
    private val fragment: Fragment
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private lateinit var viewGroup: ViewGroup

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView
        val chargerName: TextView
        val chargerStatus: TextView
        val linearLayout: LinearLayout

        init {
            image = view.findViewById(R.id.image_listitem)
            chargerName = view.findViewById(R.id.charger_name_listitem)
            chargerStatus = view.findViewById(R.id.charger_status_listitem)
            linearLayout = view.findViewById(R.id.item_linear)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.charger_list, parent, false)
        viewGroup = parent
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data: ChargerInfo = dataSet.get(position)
        Log.d("test adapter data${position}", data.toString())

        when (data.cpStat) {
            1 -> {
                holder.image.setImageResource(R.drawable.charger_on)
                holder.chargerStatus.setText("충전 가능")
            }
            2 -> {
                holder.image.setImageResource(R.drawable.charger_issue)
                holder.chargerStatus.setText("충전 중")
            }
            else -> {
                holder.image.setImageResource(R.drawable.charger_off)
                holder.chargerStatus.setText("충전 불가능")
            }
        }

        holder.chargerName.setText("${data.csNm} ${data.cpNm}")

        holder.linearLayout.setOnClickListener {
            Log.d("llss", "item clicked ${position}")
            val chargerName = dataSet.get(position).cpNm
            val builder = AlertDialog.Builder(viewGroup.context!!)
            builder.setTitle(
                "즐겨찾기 ${
                    if (isSearched) {
                        "추가"
                    } else {
                        "삭제"
                    }
                }"
            )
            builder.setMessage(
                "${chargerName}을(를) 즐겨찾기 ${
                    if (isSearched) {
                        "추가"
                    } else {
                        "삭제"
                    }
                }하시겠습니까?"
            )
            builder.setPositiveButton(
                "확인",
                DialogInterface.OnClickListener { dialogInterface, i ->
                    run {
                        if (isSearched) {
                            val callBookmarkAdd = RetrofitClient.api.addBookmark(
                                BookmarkDTO(
                                    bookmarkId = null,
                                    chargerName = "${chargerName}",
                                    chargerAddr = "${dataSet.get(position).addr}",
                                    chargerId = dataSet.get(position).csId,
                                    userId = id!!
                                )
                            )
                            callBookmarkAdd.enqueue(object : Callback<BookmarkResponse> {
                                override fun onResponse(
                                    call: Call<BookmarkResponse>,
                                    response: Response<BookmarkResponse>
                                ) {
                                    Toast.makeText(
                                        viewGroup.context,
                                        "즐겨찾기에 추가됨",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }

                                override fun onFailure(
                                    call: Call<BookmarkResponse>,
                                    t: Throwable
                                ) {
                                    Toast.makeText(viewGroup.context, "실패 400", Toast.LENGTH_SHORT)
                                        .show()
                                }

                            })
                        } else {
                            val callBookmarkDelete =
                                RetrofitClient.api.deleteBookmark(dataSet.get(position).csId, id)
                            callBookmarkDelete.enqueue(object : Callback<BookmarkResponse> {
                                override fun onResponse(
                                    call: Call<BookmarkResponse>,
                                    response: Response<BookmarkResponse>
                                ) {
                                    Toast.makeText(
                                        viewGroup.context,
                                        "즐겨찾기에서 삭제됨",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }

                                override fun onFailure(
                                    call: Call<BookmarkResponse>,
                                    t: Throwable
                                ) {
                                    Toast.makeText(viewGroup.context, "실패 400", Toast.LENGTH_SHORT)
                                        .show()
                                }

                            })
                        }

                    }
                })
            builder.setNegativeButton("취소", null)
            builder.show()
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}