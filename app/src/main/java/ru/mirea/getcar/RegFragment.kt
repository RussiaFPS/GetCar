package ru.mirea.getcar

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.mindrot.jbcrypt.BCrypt


class RegFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reg, container, false)
        val return_butt:ImageView = view.findViewById(R.id.return_butt)
        val reg_conf:ImageView = view.findViewById(R.id.reg_conf)

        val reg_login:EditText = view.findViewById(R.id.reg_login)
        val reg_pass:EditText = view.findViewById(R.id.reg_pass)
        val reg_phone:EditText = view.findViewById(R.id.reg_phone)

        return_butt.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.containerFragment, UserFragment())
                ?.commit()
        }

        reg_conf.setOnClickListener {
            val database = Firebase.database.reference
            val user = Users(
                reg_login.text.toString(), reg_pass.text.toString(),reg_phone.text.toString()
            )

            database.child("users").child(user.login.toString())
            database.child("users").child(user.login.toString()).child("password")
                .setValue(BCrypt.hashpw(user.pass, BCrypt.gensalt()))
            database.child("users").child(user.login.toString()).child("phone")
                .setValue(user.phone)
        }

        return view
    }
}