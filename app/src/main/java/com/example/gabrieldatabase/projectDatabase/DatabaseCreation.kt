package com.example.gabrieldatabase.projectDatabase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class DatabaseCreation(val context: Context?) :
      SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION)
{
    val customer1 = "CREATE TABLE ${DatabaseColumns.TABLE_STOCK_AVAILABLE} " +
            "(${DatabaseColumns.STOCK_PART_NUMBER} TEXT ,        "+
            " ${DatabaseColumns.STOCK_PART_NAME} TEXT,                      "+
            "${ DatabaseColumns.STOCK_STOCK_AVAILABLE} TEXT,                "+
            "${ DatabaseColumns.STOCK_ORDER_UNIT} TEXT,                     "+
            "${ DatabaseColumns.STOCK_VENDOR_PLANT} TEXT,                   "+
            "${ DatabaseColumns.STOCK_NET_PRICE} TEXT,                      "+
            "${ DatabaseColumns.STOCK_TOTAL_VALUE} TEXT )"

    val droptable1 = "DROP TABLE IF EXISTS ${DatabaseColumns.TABLE_STOCK_AVAILABLE}"

    val customer2 = "CREATE TABLE ${DatabaseColumns.TABLE_PBR_INWARD}   "+
            "(${DatabaseColumns.INWARD_LR_NUMBER} TEXT,                 "+
            "${ DatabaseColumns.INWARD_INVOICE_NUMBER} TEXT,            "+
            "${ DatabaseColumns.INWARD_AVAILABLE_OR_NOT} TEXT,          "+
            "${ DatabaseColumns.INWARD_DATE} TEXT,                      "+
            "${ DatabaseColumns.INWARD_PART_NO} TEXT,                   "+
            "${ DatabaseColumns.INWARD_QUANTITY} TEXT,                  "+
            "${ DatabaseColumns.INWARD_PKG} TEXT,                       "+
            "${ DatabaseColumns.INWARD_PARTS_NAME} TEXT,                "+
            "${ DatabaseColumns.INWARD_PRICE_PER_UNIT} TEXT,            "+
            "${ DatabaseColumns.INWARD_VALUE_OF_INVOICE} TEXT,          "+
            "${ DatabaseColumns.INWARD_REQ} TEXT,                       "+
            "${ DatabaseColumns.INWARD_DC_NUMBER} TEXT,                 "+
            "${ DatabaseColumns.INWARD_VENDORS_NAME} TEXT )"

    val droptable2 = "DROP TABLE IF EXISTS ${DatabaseColumns.TABLE_PBR_INWARD}"

    val customer3 = "CREATE TABLE ${DatabaseColumns.TABLE_PBR_OUTWARD}  "+
            "(${DatabaseColumns.OUTWARD_LR_NUMBER} TEXT,                "+
            "${DatabaseColumns.OUTWARD_DATE} TEXT,                      "+
            "${ DatabaseColumns.OUTWARD_PART_NO} TEXT,                  "+
            "${ DatabaseColumns.OUTWARD_PART_NAME} TEXT,                "+
            "${ DatabaseColumns.OUTWARD_QUANTITY} TEXT,                 "+
            "${ DatabaseColumns.OUTWARD_PKG} TEXT,                      "+
            "${ DatabaseColumns.OUTWARD_INVOICE_NUMBER} TEXT,           "+
            "${ DatabaseColumns.OUTWARD_DC_NUMBER} TEXT,                "+
            "${ DatabaseColumns.OUTWARD_VENDORS_NAME} TEXT )"

    val droptable3 = "DROP TABLE IF EXISTS ${DatabaseColumns.TABLE_PBR_OUTWARD}"

    val customer4 = "CREATE TABLE ${DatabaseColumns.TABLE_MONITORING}   "+
            "(${DatabaseColumns.MON_PART_NAME} TEXT,                    "+
            "${DatabaseColumns.MON_PART_NUMBER} TEXT,                   "+
            "${ DatabaseColumns.MON_QTY_INWARDED} TEXT,                 "+
            "${ DatabaseColumns.MON_QTY_OUTWARDED} TEXT)"

    val droptable4 = "DROP TABLE IF EXISTS ${DatabaseColumns.TABLE_MONITORING}"



    val customer5 = "CREATE TABLE ${DatabaseColumns.TABLE_SG_CONNECTIONS}   "+
            "(${DatabaseColumns.SG_LR_NUMBER} TEXT,                         "+
            "${DatabaseColumns.SG_AVAILABLE_OR_NOT} TEXT,                   "+
            "${ DatabaseColumns.SG_DATE} TEXT,                              "+
            "${ DatabaseColumns.SG_PART_NO} TEXT,                           "+
            "${ DatabaseColumns.SG_QUANTITY} TEXT,                          "+
            "${ DatabaseColumns.SG_PKG} TEXT,                               "+
            "${ DatabaseColumns.SG_PART_NAME} TEXT,                         "+
            "${ DatabaseColumns.SG_PRICE_PER_UNIT} TEXT,                    "+
            "${ DatabaseColumns.SG_VALUE_OF_INVOICE} TEXT,                  "+
            "${ DatabaseColumns.SG_REQ} TEXT,                               "+
            "${ DatabaseColumns.SG_INVOICE_NUMBER} TEXT,                    "+
            "${ DatabaseColumns.SG_DC_NUMBER} TEXT,                         "+
            "${ DatabaseColumns.SG_VENDORS_NAME} TEXT )"

    val droptable5 = "DROP TABLE IF EXISTS ${DatabaseColumns.TABLE_SG_CONNECTIONS}"



    val customer6 = "CREATE TABLE ${DatabaseColumns.TABLE_SIGNUP}           "+
            "(${DatabaseColumns.SU_NAME} TEXT,                              "+
            "${DatabaseColumns.SU_EMAIL} TEXT PRIMARY KEY,                  "+
            "${ DatabaseColumns.SU_PHONE} LONG,                             "+
            "${ DatabaseColumns.SU_PASSWORD} TEXT)"

    val droptable6 = "DROP TABLE IF EXISTS ${DatabaseColumns.TABLE_SIGNUP}"



    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int)
    {
        db!!.execSQL(droptable1)
        db.execSQL(droptable2)
        db.execSQL(droptable3)
        db.execSQL(droptable4)
        db.execSQL(droptable5)
        db.execSQL(droptable6)
        onCreate(db)
    }

    override fun onCreate(db: SQLiteDatabase?)
    {
        db!!.execSQL(customer1)
        db.execSQL(customer2)
        db.execSQL(customer3)
        db.execSQL(customer4)
        db.execSQL(customer5)
        db.execSQL(customer6)
    }

    companion object
    {
        val DB_NAME = "Warehouse.db"
        val DB_VERSION =1
    }

    fun backup(outFileName: String) {

        //database path
        val inFileName = context!!.getDatabasePath(DB_NAME).toString()

        try {

            val dbFile = File(inFileName)
            val fis = FileInputStream(dbFile)

            // Open the empty db as the output stream
            val output = FileOutputStream(outFileName)

            // Transfer bytes from the input file to the output file
            val buffer = ByteArray(1024)
            var length: Int
            while (fis.read(buffer).also { length = it } > 0){
                output.write(buffer, 0, length)
            }

            // Close the streams
            output.flush()
            output.close()
            fis.close()

            Toast.makeText(context, "Backup Completed", Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
            Toast.makeText(context, "Unable to backup database. Retry", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }

    }

    fun importDB(inFileName: String) {

        val outFileName = context!!.getDatabasePath(DB_NAME).toString()

        try {

            val dbFile = File(inFileName)
            val fis = FileInputStream(dbFile)

            // Open the empty db as the output stream
            val output = FileOutputStream(outFileName)

            // Transfer bytes from the input file to the output file
            val buffer = ByteArray(1024)
            var length: Int

            while (fis.read(buffer).also { length = it } > 0) {
                output.write(buffer, 0, length)
            }

            // Close the streams
            output.flush()
            output.close()
            fis.close()

            Toast.makeText(context, "Import Completed", Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
            Toast.makeText(context, "Unable to import database. Retry", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }

    }
}