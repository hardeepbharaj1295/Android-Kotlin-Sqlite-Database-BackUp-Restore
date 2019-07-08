package com.example.gabrieldatabase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gabrieldatabase.R
import com.example.gabrieldatabase.adapters.StockAdapter
import com.example.gabrieldatabase.model.StockContent

import com.example.gabrieldatabase.model.StockContent.StockItem
import com.example.gabrieldatabase.projectDatabase.DatabaseColumns
import com.example.gabrieldatabase.projectDatabase.DatabaseQueries


class StockFragment : Fragment() {

    private var columnCount = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_stock_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                val db = DatabaseQueries(requireContext())
                val result = db.get_stock()
                if (result.moveToFirst())
                {
                    do{
                        val item = StockItem(result.getString(result.getColumnIndex(DatabaseColumns.STOCK_PART_NUMBER)),
                            result.getString(result.getColumnIndex(DatabaseColumns.SG_PART_NAME)),
                            result.getString(result.getColumnIndex(DatabaseColumns.STOCK_STOCK_AVAILABLE)),
                            result.getString(result.getColumnIndex(DatabaseColumns.STOCK_ORDER_UNIT)),
                            result.getString(result.getColumnIndex(DatabaseColumns.STOCK_VENDOR_PLANT)),
                            result.getString(result.getColumnIndex(DatabaseColumns.STOCK_NET_PRICE)),
                            result.getString(result.getColumnIndex(DatabaseColumns.STOCK_TOTAL_VALUE)))
                        StockContent.ITEMS.add(item)
                    }
                    while (result.moveToNext());
                    adapter = StockAdapter(StockContent.ITEMS)
                }
            }
        }
        return view
    }
}
