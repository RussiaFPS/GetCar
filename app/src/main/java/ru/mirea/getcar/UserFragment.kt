package ru.mirea.getcar

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class UserFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user, container, false)
        val reg_button: ImageView = view.findViewById(R.id.reg_button)
        val auth_button: ImageView = view.findViewById(R.id.auth_button)
        val user_name:TextView = view.findViewById(R.id.textView15)

        val sharedPref = activity?.getPreferences(MODE_PRIVATE)
        val login = sharedPref?.getString("login", "Пользователь")
        user_name.text=login

        if(login!="Пользователь"){
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.containerFragment, ProfileFragment())
                ?.commit()
        }

        reg_button.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.containerFragment, RegFragment())
                ?.commit()
        }

        auth_button.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.containerFragment, AuthFragment())
                ?.commit()
        }

        return view
    }
}