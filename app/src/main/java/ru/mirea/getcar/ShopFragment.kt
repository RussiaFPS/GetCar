package ru.mirea.getcar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ShopFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_shop, container, false)
        val listViewCars = view.findViewById<ListView>(R.id.listViewCars)

        val database = Firebase.database.reference
        var cars:MutableList<Cars> = mutableListOf()
        database.child("cars").get().addOnSuccessListener { it1 ->
            var count = 0

            while (count<it1.childrenCount) {
                database.child("cars").child(count.toString()).child("model").get().addOnSuccessListener { it2 ->
                    cars.add(Cars(it2.value.toString()))
                    var customAdapter = CustomAdapter(this,cars)
                    listViewCars.adapter = customAdapter
                }

                count++
            }
        }

        /*var customAdapter = CustomAdapter(this)
        listViewCars.adapter = customAdapter

        listViewCars.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(context,"Item"+ customAdapter.names[i],Toast.LENGTH_LONG).show()
        }
        */
        return view
    }
}

class CustomAdapter(private val context: ShopFragment,private val cars:MutableList<Cars>): BaseAdapter(){
    //val names = arrayOf("Hello","id","name","123","Hello","id","name","123","Hello","id","name","123","Hello","id","name","123","Hello","id","name","name")

    override fun getCount(): Int {
        return cars.size
    }

    override fun getItem(p0: Int): Any {
        return cars[p0]
    }

    override fun getItemId(p0: Int): Long {
       return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var inflater = context.layoutInflater
        var view1 = inflater.inflate(R.layout.list_item,null)

        var text = view1.findViewById<TextView>(R.id.item_name_cars)

        text.text = cars[p0].model
        return view1
    }

}
