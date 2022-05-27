package ru.mirea.getcar

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.marginLeft
import androidx.core.view.marginStart
import androidx.fragment.app.Fragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class MyReqFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_my_req, container, false)
        val table:TableLayout = view.findViewById(R.id.TableReq)
        val return_butt: ImageView = view.findViewById(R.id.return_butt)
        val database = Firebase.database.reference
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val login = sharedPref?.getString("login", "")


        /*database.child("orders").child("0").child(login.toString()).child("model")
            .setValue("Test")
        database.child("orders").child("0").child(login.toString()).child("status")
            .setValue("Обрабатывается")*/

       //val items_data: MutableList<Orders> = mutableListOf<Orders>()

        database.child("orders").get().addOnSuccessListener {
            var count = 0
            while (count<it.childrenCount){
                var model:String=""
                var status:String=""

                database.child("orders").child(count.toString()).child(login.toString())
                    .child("model").get().addOnSuccessListener { it1 ->
                        if(it1.value!=null) {
                            model = it1.value.toString()
                        }
                    }
                database.child("orders").child(count.toString()).child(login.toString())
                    .child("status").get().addOnSuccessListener { it2 ->
                        if(it2.value!=null){
                            status = it2.value.toString()

                            val tr = TableRow(context)
                            val md = TextView(context)
                            md.text = model
                            tr.addView(md)
                            val st = TextView(context)
                            st.text = status
                            tr.addView(st)
                            table.addView(tr)
                        }
                    }

                count++
            }
        }


        /*database.child("orders").get().addOnSuccessListener {
            var count = 0
            while (count<it.childrenCount){
                var model:String=""
                var status:String=""

                database.child("orders").child(count.toString()).child(login.toString())
                    .child("model").get().addOnSuccessListener { it1 ->
                        if(it1.value.toString()!="") {
                            model = it1.value.toString()
                        }
                }
                database.child("orders").child(count.toString()).child(login.toString())
                    .child("status").get().addOnSuccessListener { it2 ->
                        if(it2.value!=null){
                            status = it2.value.toString()
                        }
                    }

                Toast.makeText(context, status, Toast.LENGTH_SHORT)
                    .show()
                count++
            }
        }*/

        /*for (items in items_data) {
            val tr = TableRow(context)
            val model = TextView(context)
            model.text = items.model
            tr.addView(model)
            val status = TextView(context)
            status.text = items.status
            tr.addView(status)
            table.addView(tr)
        }*/

        return_butt.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.containerFragment, ProfileFragment())
                ?.commit()
        }

        return view
    }
}