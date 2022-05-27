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
import org.w3c.dom.Text
import java.sql.Time
import kotlin.random.Random


class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_main, container, false)
        val user_name:TextView = view.findViewById(R.id.textView4)
        val firstCar:ImageView = view.findViewById(R.id.firstCar)
        val nameFirstCar:TextView = view.findViewById(R.id.textView5)
        val secondCar:ImageView = view.findViewById(R.id.imageView10)
        val nameSecondCar:TextView = view.findViewById(R.id.textView11)

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val login = sharedPref?.getString("login", "Пользователь")
        user_name.text=login


        val database = Firebase.database.reference

        /*
        database.child("cars").child("0").child("model")
            .setValue("porsche_cayman")
        database.child("cars").child("0").child("image")
            .setValue(this.resources.getIdentifier("porsche_cayman", "drawable", context?.packageName))
        database.child("cars").child("0").child("cost")
            .setValue("7000000")
        database.child("cars").child("0").child("description")
            .setValue("TEST")

        database.child("cars").child("1").child("model")
            .setValue("mercedes_gt")
        database.child("cars").child("1").child("image")
            .setValue(this.resources.getIdentifier("mercedes_gt", "drawable", context?.packageName))
        database.child("cars").child("1").child("cost")
            .setValue("6000000")
        database.child("cars").child("1").child("description")
            .setValue("TEST")

        database.child("cars").child("2").child("model")
            .setValue("mercedes_s500")
        database.child("cars").child("2").child("image")
            .setValue(this.resources.getIdentifier("mercedes_s500", "drawable", context?.packageName))
        database.child("cars").child("2").child("cost")
            .setValue("13000000")
        database.child("cars").child("2").child("description")
            .setValue("TEST")

        database.child("cars").child("3").child("model")
            .setValue("bmw_m2")
        database.child("cars").child("3").child("image")
            .setValue(this.resources.getIdentifier("bmw_m2", "drawable", context?.packageName))
        database.child("cars").child("3").child("cost")
            .setValue("4000000")
        database.child("cars").child("3").child("description")
            .setValue("TEST")
        */

        /*
        val map = mapOf("porsche_cayman" to this.resources.getIdentifier("porsche_cayman", "drawable", context?.packageName),
            "mercedes_gt" to this.resources.getIdentifier("mercedes_gt", "drawable", context?.packageName),
            "mercedes_s500" to this.resources.getIdentifier("mercedes_s500", "drawable", context?.packageName),
            "bmw_m2" to this.resources.getIdentifier("bmw_m2", "drawable", context?.packageName)
        )*/

        database.child("cars").get().addOnSuccessListener {
            val rd: Long = (0 until it.childrenCount).random()
            database.child("cars").child(rd.toString()).child("image").get()
                .addOnSuccessListener { it1 ->
                    database.child("cars").child(rd.toString()).child("model").get()
                        .addOnSuccessListener { it2 ->
                            firstCar.setImageDrawable(resources.getDrawable(it1.value.toString().toInt()))
                            nameFirstCar.text = it2.value.toString()
                        }
                }
        }

        database.child("cars").get().addOnSuccessListener {
            val rd: Long = (0 until it.childrenCount).random()
            database.child("cars").child( rd.toString()).child("image").get().addOnSuccessListener { it1 ->
                database.child("cars").child(rd.toString()).child("model").get()
                    .addOnSuccessListener { it2 ->
                        secondCar.setImageDrawable(
                            resources.getDrawable(it1.value.toString().toInt()))
                            nameSecondCar.text = it2.value.toString()
                    }
            }
        }

        return view
    }
}