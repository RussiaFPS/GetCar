package ru.mirea.getcar

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.mindrot.jbcrypt.BCrypt


class AuthFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_auth, container, false)
        val return_butt: ImageView = view.findViewById(R.id.return_butt_auth)
        val auth_login: EditText = view.findViewById(R.id.auth_login)
        val auth_pass: EditText = view.findViewById(R.id.auth_pass)
        val auth_conf:ImageView = view.findViewById(R.id.auth_conf)

        return_butt.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.containerFragment, UserFragment())
                ?.commit()
        }

        auth_conf.setOnClickListener {
            val database = Firebase.database.reference

            database.child("users").child(auth_login.text.toString()).child("password").get().addOnSuccessListener {
                if (it.value!=null) {
                    if(BCrypt.checkpw(auth_pass.text.toString(), it.value.toString())){
                        Toast.makeText(context, "Успешно!", Toast.LENGTH_SHORT)
                            .show()

                        val sharedPref = activity?.getPreferences(MODE_PRIVATE)
                        if (sharedPref != null) {
                            with (sharedPref.edit()) {
                                putString("login", auth_login.text.toString())
                                apply()
                            }
                        }

                    }else{
                        Toast.makeText(context, "Неверный логин или пароль!", Toast.LENGTH_SHORT)
                            .show()
                    }
                }else{
                    Toast.makeText(context, "Неверный логин или пароль!", Toast.LENGTH_SHORT)
                        .show()
                }
            }.addOnFailureListener{
                Toast.makeText(context, "Ошибка подключения к БД!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        return view
    }


}