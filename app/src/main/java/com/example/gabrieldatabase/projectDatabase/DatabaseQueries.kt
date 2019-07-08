package com.example.gabrieldatabase.projectDatabase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log

class DatabaseQueries(context: Context) {

    private var sql:DatabaseCreation = DatabaseCreation(context)

    fun availableStock(part_number: String, part_name: String, stock_available: String, order_unit:
                       String, vendor_plant: String, net_price: String, total_value: String):Long
    {
        val db = sql.writableDatabase
        val cv = ContentValues()
        cv.put(DatabaseColumns.STOCK_PART_NUMBER,part_number )
        cv.put(DatabaseColumns.STOCK_PART_NAME, part_name)
        cv.put(DatabaseColumns.STOCK_STOCK_AVAILABLE, stock_available)
        cv.put(DatabaseColumns.STOCK_ORDER_UNIT,order_unit )
        cv.put(DatabaseColumns.STOCK_VENDOR_PLANT,vendor_plant )
        cv.put(DatabaseColumns.STOCK_NET_PRICE,net_price )
        cv.put(DatabaseColumns.STOCK_TOTAL_VALUE,total_value )
        return db.insert(DatabaseColumns.TABLE_STOCK_AVAILABLE, null, cv)
    }

    fun pbrInward(LrNumber: String,InvoiceNumber: String,AvailableOrNot: String,Date: String,
                  PartNo: String,Quantity: String,PKG: String,PartName: String,PricePerUnit: String,
                  ValueOfInvoice: String,req: String,DCNumber: String,VendorsName: String): Long
    {
        val db = sql.writableDatabase
        val cv = ContentValues()
        cv.put(DatabaseColumns.INWARD_LR_NUMBER,LrNumber )
        cv.put(DatabaseColumns.INWARD_INVOICE_NUMBER, InvoiceNumber)
        cv.put(DatabaseColumns.INWARD_AVAILABLE_OR_NOT,AvailableOrNot)
        cv.put(DatabaseColumns.INWARD_DATE,Date )
        cv.put(DatabaseColumns.INWARD_PART_NO,PartNo )
        cv.put(DatabaseColumns.INWARD_QUANTITY,Quantity )
        cv.put(DatabaseColumns.INWARD_PKG,PKG )
        cv.put(DatabaseColumns.INWARD_PARTS_NAME,PartName )
        cv.put(DatabaseColumns.INWARD_PRICE_PER_UNIT,PricePerUnit )
        cv.put(DatabaseColumns.INWARD_VALUE_OF_INVOICE,ValueOfInvoice )
        cv.put(DatabaseColumns.INWARD_REQ,req)
        cv.put(DatabaseColumns.INWARD_DC_NUMBER,DCNumber )
        cv.put(DatabaseColumns.INWARD_VENDORS_NAME,VendorsName )
        return db.insert(DatabaseColumns.TABLE_PBR_INWARD, null, cv)
    }

    fun pbrOutward(Lr_Number: String,Date: String,PartNo: String,Part_Name: String,
                   Quantity: String,PKG: String,Invoice_Number: String,DC_Number: String,
                   Vendors_Name: String): Long
    {
        val db = sql.writableDatabase
        val cv = ContentValues()
        cv.put(DatabaseColumns.OUTWARD_LR_NUMBER,Lr_Number )
        cv.put(DatabaseColumns.OUTWARD_DATE,Date )
        cv.put(DatabaseColumns.OUTWARD_PART_NO,PartNo )
        cv.put(DatabaseColumns.OUTWARD_PART_NAME,Part_Name )
        cv.put(DatabaseColumns.OUTWARD_QUANTITY,Quantity )
        cv.put(DatabaseColumns.OUTWARD_PKG,PKG)
        cv.put(DatabaseColumns.OUTWARD_INVOICE_NUMBER, Invoice_Number)
        cv.put(DatabaseColumns.OUTWARD_DC_NUMBER,DC_Number )
        cv.put(DatabaseColumns.OUTWARD_VENDORS_NAME,Vendors_Name )
        return db.insert(DatabaseColumns.TABLE_PBR_OUTWARD, null, cv)
    }

    fun monitoring(partname: String, partnumber: String, qytinward: String, qytoutward: String): Long
    {
        val db = sql.writableDatabase
        val cv = ContentValues()
        cv.put(DatabaseColumns.MON_PART_NAME,partname)
        cv.put(DatabaseColumns.MON_PART_NUMBER,partnumber)
        cv.put(DatabaseColumns.MON_QTY_INWARDED,qytinward)
        cv.put(DatabaseColumns.MON_QTY_OUTWARDED,qytoutward)
        return db.insert(DatabaseColumns.TABLE_MONITORING,null, cv)
    }

    fun sgConnections(LrNumber: String,AvailableOrNot: String,Date: String,
                  PartNo: String,Quantity: String,PKG: String,PartName: String,PricePerUnit: String,
                  ValueOfInvoice: String,req: String,InvoiceNumber: String,DCNumber: String,VendorsName: String): Long
    {
        val db = sql.writableDatabase
        val cv = ContentValues()
        cv.put(DatabaseColumns.SG_LR_NUMBER,LrNumber )
        cv.put(DatabaseColumns.SG_AVAILABLE_OR_NOT,AvailableOrNot)
        cv.put(DatabaseColumns.SG_DATE,Date )
        cv.put(DatabaseColumns.SG_PART_NO,PartNo )
        cv.put(DatabaseColumns.SG_QUANTITY,Quantity )
        cv.put(DatabaseColumns.SG_PKG,PKG )
        cv.put(DatabaseColumns.SG_PART_NAME,PartName )
        cv.put(DatabaseColumns.SG_PRICE_PER_UNIT,PricePerUnit )
        cv.put(DatabaseColumns.SG_VALUE_OF_INVOICE,ValueOfInvoice )
        cv.put(DatabaseColumns.SG_REQ,req)
        cv.put(DatabaseColumns.SG_INVOICE_NUMBER, InvoiceNumber)
        cv.put(DatabaseColumns.SG_DC_NUMBER,DCNumber )
        cv.put(DatabaseColumns.SG_VENDORS_NAME,VendorsName )
        return db.insert(DatabaseColumns.TABLE_SG_CONNECTIONS, null, cv)
    }

    fun signUp(name: String, email: String, phone: Long, password: String): Long
    {
        val db = sql.writableDatabase
        val cv = ContentValues()
        cv.put(DatabaseColumns.SU_NAME,name)
        cv.put(DatabaseColumns.SU_EMAIL,email)
        cv.put(DatabaseColumns.SU_PHONE,phone)
        cv.put(DatabaseColumns.SU_PASSWORD,password)
        return db.insert(DatabaseColumns.TABLE_SIGNUP,null, cv)
    }

    fun login(email: String, password: String):Cursor
    {
        val db = sql.readableDatabase
        return db.query(DatabaseColumns.TABLE_SIGNUP,null,DatabaseColumns.SU_EMAIL+"=? AND "+DatabaseColumns.SU_PASSWORD+"=?",
            arrayOf(email,password), null,null,null)
    }

    fun get_inward(invoice: String):Cursor
    {
        Log.e("invoice data",invoice)
        val db = sql.readableDatabase
        return db.query(DatabaseColumns.TABLE_PBR_INWARD,null,DatabaseColumns.INWARD_INVOICE_NUMBER+"=?",
            arrayOf(invoice),null,null,null)
    }

    fun get_outward(invoice: String):Cursor
    {
        val db = sql.readableDatabase
        return db.query(DatabaseColumns.TABLE_PBR_OUTWARD,null,DatabaseColumns.OUTWARD_INVOICE_NUMBER+"=?",
            arrayOf(invoice),null,null,null)
    }

    fun get_connection(incoive: String):Cursor
    {
        val db = sql.readableDatabase
        return db.query(DatabaseColumns.TABLE_SG_CONNECTIONS,null,DatabaseColumns.SG_VALUE_OF_INVOICE+"=?",
            arrayOf(incoive),null,null,null)
    }

    fun get_stock():Cursor
    {
        val db = sql.readableDatabase
        return db.query(DatabaseColumns.TABLE_STOCK_AVAILABLE,null,null,
            null,null,null,null)
    }
}