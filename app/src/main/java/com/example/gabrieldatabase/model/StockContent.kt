package com.example.gabrieldatabase.model

import java.util.ArrayList
import java.util.HashMap


object StockContent {

    val ITEMS: ArrayList<StockItem> = ArrayList()

    data class StockItem(val part_no: String, val part_name: String, val stock_available: String,
                         val order_unit: String, val vendor: String,
                         val net_price: String, val total: String) {
        override fun toString(): String = part_no
    }
}
