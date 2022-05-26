package ru.mirea.getcar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.marginLeft
import androidx.core.view.marginStart
import androidx.fragment.app.Fragment


class MyReqFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_my_req, container, false)
        val table:TableLayout = view.findViewById(R.id.TableReq)
        val return_butt: ImageView = view.findViewById(R.id.return_butt)
        val items_data: Array<String> = arrayOf<String>(
            ( "Samsung"),
            ("Samsung1")
        )

        for (items in items_data) {
            val tr = TableRow(context)
            val model = TextView(context)
            model.text = items
            tr.addView(model)
            val status = TextView(context)
            status.text = "hello"
            tr.addView(status)
            table.addView(tr)
        }

        return_butt.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.containerFragment, ProfileFragment())
                ?.commit()
        }

        return view
    }
}