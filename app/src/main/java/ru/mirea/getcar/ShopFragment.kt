package ru.mirea.getcar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
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
            var model:String=""
            var image:String=""
            var cost:String=""

            while (count<it1.childrenCount) {
                database.child("cars").child(count.toString()).child("model").get().addOnSuccessListener { it2 ->
                   model=it2.value.toString()
                }

                database.child("cars").child(count.toString()).child("image").get().addOnSuccessListener { it3 ->
                    image = it3.value.toString()
                }

                database.child("cars").child(count.toString()).child("cost").get().addOnSuccessListener { it4 ->
                    cost = it4.value.toString()
                    cars.add(Cars(model,image,cost))
                    var customAdapter = CustomAdapter(this, cars)
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

        val text = view1.findViewById<TextView>(R.id.item_name_cars)
        val item_price_cars = view1.findViewById<TextView>(R.id.item_price_cars)
        val item_img_cars = view1.findViewById<ImageView>(R.id.item_img_cars)

        text.text = cars[p0].model
        item_price_cars.text = cars[p0].cost
        item_img_cars.setImageDrawable(this.context.resources.getDrawable(cars[p0].image.toString().toInt()))

        return view1
    }

}
