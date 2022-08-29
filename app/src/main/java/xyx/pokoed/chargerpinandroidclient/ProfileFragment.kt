package xyx.pokoed.chargerpinandroidclient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class ProfileFragment : Fragment() {

    private lateinit var logoutBtn: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.fragment_profile, container, false)

        logoutBtn = v.findViewById(R.id.btn_logout)

        val pref =
            requireActivity().getSharedPreferences("userInfo", AppCompatActivity.MODE_PRIVATE)

        logoutBtn.setOnClickListener {
            pref.edit().clear().commit()

            App.isLogined = false

            requireActivity().finish()
            requireActivity().overridePendingTransition(0, 0)
            requireActivity().startActivity(requireActivity().intent)
            requireActivity().overridePendingTransition(0, 0)
        }

        return v
    }
}