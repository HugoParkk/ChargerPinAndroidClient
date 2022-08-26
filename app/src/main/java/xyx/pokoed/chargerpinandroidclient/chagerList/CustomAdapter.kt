package xyx.pokoed.chargerpinandroidclient.chagerList

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import xyx.pokoed.chargerpinandroidclient.R
import xyx.pokoed.chargerpinandroidclient.chagerList.data.Body
import xyx.pokoed.chargerpinandroidclient.chagerList.data.ChargerInfo

class CustomAdapter(private val dataSet: Array<ChargerInfo>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val image: ImageView
        val chargerName: TextView
        val chargerStatus: TextView

        init {
            image = view.findViewById(R.id.image_listitem)
            chargerName = view.findViewById(R.id.charger_name_listitem)
            chargerStatus = view.findViewById(R.id.charger_status_listitem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.charger_list, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: ChargerInfo = dataSet.get(position)
        Log.d("test adapter data${position}", data.toString())

        when(data.cpStat) {
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
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}