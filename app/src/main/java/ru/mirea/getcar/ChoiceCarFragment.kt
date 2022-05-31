package ru.mirea.getcar

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
        val bundle = arguments
        val idCar = bundle!!.getInt("idCar")
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


        return_butt.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.containerFragment, ShopFragment())
                ?.commit()
        }

        return view
    }
}