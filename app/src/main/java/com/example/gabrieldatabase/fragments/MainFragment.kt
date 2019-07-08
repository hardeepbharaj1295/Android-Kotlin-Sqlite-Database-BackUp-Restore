package com.example.gabrieldatabase.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import com.example.gabrieldatabase.R

class MainFragment : Fragment() {

    private lateinit var editText: EditText
    private lateinit var button: Button
    companion object{
        lateinit var invoice: String
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_main, container, false)

        editText = v.findViewById(R.id.editText)
        button = v.findViewById(R.id.button)

        button.setOnClickListener(View.OnClickListener {
            if (editText.text.toString() == "")
            {
                Toast.makeText(requireContext(),"Enter Invoice Number",Toast.LENGTH_LONG).show()
            }
            else
            {
                invoice = editText.text.toString()
                Toast.makeText(requireContext(),"Check Details",Toast.LENGTH_LONG).show()
            }
        })

        return v
    }

}
