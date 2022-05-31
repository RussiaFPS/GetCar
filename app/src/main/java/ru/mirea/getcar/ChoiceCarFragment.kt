package ru.mirea.getcar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment

class ChoiceCarFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_choice_car, container, false)
        val return_butt:ImageView = view.findViewById(R.id.return_butt)
        val bundle = arguments
        val idCar = bundle!!.getInt("idCar")

        return_butt.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.containerFragment, ShopFragment())
                ?.commit()
        }

        return view
    }
}