package com.example.gabrieldatabase.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

import com.example.gabrieldatabase.R
import com.example.gabrieldatabase.projectDatabase.DatabaseColumns
import com.example.gabrieldatabase.projectDatabase.DatabaseQueries


class PBROutwardFragment : Fragment() {

    lateinit var lrno: TextView
    lateinit var invoiceno: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_pbroutward, container, false)
        lrno = v.findViewById(R.id.lrno)
        invoiceno = v.findViewById(R.id.invoiceno)

        val data = MainFragment.invoice.toFloat()


        Toast.makeText(requireContext(),data.toString(), Toast.LENGTH_LONG).show()
        val db = DatabaseQueries(requireContext())
        val cursor = db.get_outward(data.toString())
        if (cursor.moveToFirst())
        {
            val lrnotxt = cursor.getString(cursor.getColumnIndex(DatabaseColumns.OUTWARD_LR_NUMBER))
            val invtxt = cursor.getString(cursor.getColumnIndex(DatabaseColumns.OUTWARD_INVOICE_NUMBER))

            lrno.text = "Lr NO. = $lrnotxt"
            invoiceno.text = invtxt
        }
        return v
    }
}
