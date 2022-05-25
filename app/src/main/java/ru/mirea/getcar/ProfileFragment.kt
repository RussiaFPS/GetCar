package ru.mirea.getcar

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val user_name: TextView = view.findViewById(R.id.ProfileLogin)

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val login = sharedPref?.getString("login", "Пользователь")
        if (login!="Пользователь"){
            user_name.text=login
        }else{
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.containerFragment, UserFragment())
                ?.commit()
        }

        return view
    }
}