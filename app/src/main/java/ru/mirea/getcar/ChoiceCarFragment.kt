package ru.mirea.getcar

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ChoiceCarFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_choice_car, container, false)
        val return_butt:ImageView = view.findViewById(R.id.return_butt)
        val ChoiceCarName:TextView = view.findViewById(R.id.ChoiceCarName)
        val ChoiceCarImage:ImageView = view.findViewById(R.id.ChoiceCarImage)
        val ChoiceCarCost:TextView = view.findViewById(R.id.ChoiceCarCost)
        val ChoiceCarDesc:TextView = view.findViewById(R.id.ChoiceCarDesc)
        val choice_butt_req:ImageView = view.findViewById(R.id.choice_butt_req)
        val bundle = arguments
        val idCar = bundle!!.getInt("idCar")
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val login = sharedPref?.getString("login", "Пользователь")
        val database = Firebase.database.reference

        database.child("cars").child(idCar.toString()).child("model").get().addOnSuccessListener {
            database.child("cars").child(idCar.toString()).child("image").get().addOnSuccessListener { it1 ->
                database.child("cars").child(idCar.toString()).child("description").get().addOnSuccessListener { it2 ->
                    database.child("cars").child(idCar.toString()).child("cost").get().addOnSuccessListener { it3 ->
                        ChoiceCarName.text = it.value.toString()
                        ChoiceCarImage.setImageDrawable(requireContext().resources.getDrawable(it1.value.toString().toInt()))
                        ChoiceCarDesc.text = it2.value.toString()
                        ChoiceCarCost.text = it3.value.toString()
                    }
                }
            }
        }

       choice_butt_req.setOnClickListener {
           if (login!="Пользователь"){
               database.child("orders").get().addOnSuccessListener {
                   val count:Long = it.childrenCount

                   database.child("orders").child(count.toString()).child(login.toString()).child("model")
                        .setValue(ChoiceCarName.text.toString())
                    database.child("orders").child(count.toString()).child(login.toString()).child("status")
                        .setValue("Обрабатывается")
                   Toast.makeText(context, "Заявка создана!", Toast.LENGTH_SHORT)
                       .show()
               }
           }else{
               Toast.makeText(context, "Для этой функции нужно авторизоваться!", Toast.LENGTH_SHORT)
                   .show()
           }
       }

        return_butt.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.containerFragment, ShopFragment())
                ?.commit()
        }

        return view
    }
}