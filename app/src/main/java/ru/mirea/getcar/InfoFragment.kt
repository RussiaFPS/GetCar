package ru.mirea.getcar

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class InfoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_info, container, false)
        val phoneValue:TextView = view.findViewById(R.id.textView12)
        val mailValue:TextView = view.findViewById(R.id.textView10)

        phoneValue.setOnClickListener {
            try {
                val number: Long = phoneValue.text.toString().toLong()
                startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number")))
            }catch (nfe: NumberFormatException){
                Toast.makeText(context, "Что-то пошло не так", Toast.LENGTH_SHORT).show()
            }
        }

        mailValue.setOnClickListener {
            val mail: String = mailValue.text.toString()
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("mailto:$mail")))
        }

        return view
    }

}