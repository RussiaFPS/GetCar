package ru.mirea.getcar

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.mindrot.jbcrypt.BCrypt

class SettingsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        val return_butt: ImageView = view.findViewById(R.id.return_butt)
        val reg_conf:ImageView = view.findViewById(R.id.reg_conf)
        val new_pass:EditText = view.findViewById(R.id.new_pass)
        val new_phone:EditText = view.findViewById(R.id.new_phone)
        val isChangePass:CheckBox = view.findViewById(R.id.isChangePass)
        val isChangePhone:CheckBox = view.findViewById(R.id.isChangePhone)

        reg_conf.setOnClickListener {
            if(isChangePass.isChecked){
                if (new_pass.text.toString().length in 18 downTo 8) {
                    val database = Firebase.database.reference
                    val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
                    val login = sharedPref?.getString("login", "")

                    database.child("users").child(login.toString()).child("password")
                        .setValue(BCrypt.hashpw(new_pass.text.toString(), BCrypt.gensalt()))
                    Toast.makeText(context, "Пароль изменен успешно!", Toast.LENGTH_SHORT)
                        .show()
                }else{
                    Toast.makeText(context, "Длинна пароля от 8 до 18 символов!", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            if(isChangePhone.isChecked){
                if(new_phone.text.toString().isNotEmpty()) {
                    val database = Firebase.database.reference
                    val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
                    val login = sharedPref?.getString("login", "")

                    database.child("users").child(login.toString()).child("phone")
                        .setValue(new_phone.text.toString())
                    Toast.makeText(context, "Номер телефона изменен успешно!", Toast.LENGTH_SHORT)
                        .show()
                }else{
                    Toast.makeText(context, "Введите номер телефона!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        return_butt.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.containerFragment, ProfileFragment())
                ?.commit()
        }

        return view
    }
}