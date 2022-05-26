package ru.mirea.getcar

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val user_name: TextView = view.findViewById(R.id.ProfileLogin)
        val exit_button:ImageView = view.findViewById(R.id.exit_button)
        val settings_button:ImageView = view.findViewById(R.id.settings_button)
        val my_req_button:ImageView = view.findViewById(R.id.my_req_button)

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val login = sharedPref?.getString("login", "Пользователь")
        if (login!="Пользователь"){
            user_name.text=login
        }else{
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.containerFragment, UserFragment())
                ?.commit()
        }

        my_req_button.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.containerFragment, MyReqFragment())
                ?.commit()
        }

        settings_button.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.containerFragment, SettingsFragment())
                ?.commit()
        }

        exit_button.setOnClickListener {
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
            sharedPref?.edit()?.remove("login")?.apply()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.containerFragment, UserFragment())
                ?.commit()
            Toast.makeText(context, "Вы вышли из аккаунта!", Toast.LENGTH_SHORT)
                .show()
        }

        return view
    }
}