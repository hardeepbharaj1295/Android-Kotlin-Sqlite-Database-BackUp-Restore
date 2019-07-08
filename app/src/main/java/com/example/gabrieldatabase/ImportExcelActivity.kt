package com.example.gabrieldatabase

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import com.example.gabrieldatabase.projectDatabase.DatabaseQueries
import org.apache.poi.hssf.usermodel.HSSFCell
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.poifs.filesystem.POIFSFileSystem
import java.io.File
import java.io.FileInputStream
import java.io.InputStream

class ImportExcelActivity : AppCompatActivity() {

    fun permissions():Boolean
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1)
                return false
            }
            return true
        }
        return true
    }

    fun stock(v:View)
    {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("*/*")
        startActivityForResult(intent,1)
    }

    fun inward(v:View)
    {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("*/*")
        startActivityForResult(intent,2)
    }

    fun outward(v:View)
    {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("*/*")
        startActivityForResult(intent,3)
    }

    fun monitoring(v:View)
    {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("*/*")
        startActivityForResult(intent,4)
    }

    fun connection(v:View)
    {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("*/*")
        startActivityForResult(intent,5)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_import_excel)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,HomeActivity::class.java))
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            1->{
                val path = data!!.data.path
                readExcelFileFromAssets(path,1)
            }
            2->{
                val path = data!!.data.path
                readExcelFileFromAssets(path,2)
            }
            3->{
                val path = data!!.data.path
                readExcelFileFromAssets(path,3)
            }
            4->{
                val path = data!!.data.path
                readExcelFileFromAssets(path,4)
            }
            5->{
                val path = data!!.data.path
                readExcelFileFromAssets(path,5)
            }
        }
    }

    fun readExcelFileFromAssets(path:String,id: Int)
    {
        try {
            Log.e("PAth",path)
            val data = path.replace("/file_share","")
//            val data = Environment.getExternalStorageDirectory().toString() + path.replace("/external_files","")

            val myInput: InputStream
            myInput = FileInputStream(data)
            val myFileSystem = POIFSFileSystem(myInput)
            val myWorkBook = HSSFWorkbook(myFileSystem)
            val mySheet = myWorkBook.getSheetAt(0)
            val rowIter = mySheet.rowIterator()
            var rowno = 0

            if (id == 1)
            {
                while (rowIter.hasNext()) {

                    val myRow = rowIter.next() as HSSFRow
                    if (rowno != 0) {
                        val cellIter = myRow.cellIterator()
                        var colno = 0
                        var sno = ""
                        var name = ""
                        var stock = ""
                        var order = ""
                        var vendor = ""
                        var price = ""
                        var total = ""
                        while (cellIter.hasNext()) {
                            val myCell = cellIter.next() as HSSFCell
                            if (colno == 0) {
                                sno = myCell.toString()
                            } else if (colno == 1) {
                                name = myCell.toString()
                            } else if (colno == 2) {
                                stock = myCell.toString()
                            } else if (colno == 3) {
                                order = myCell.toString()
                            } else if (colno == 4) {
                                vendor = myCell.toString()
                            } else if (colno == 5) {
                                price = myCell.toString()
                            } else if (colno == 6) {
                                total = myCell.toString()
                            }
                            colno++
                            //Log.e("Data", " Index :" + myCell.columnIndex + " -- " + myCell.toString())
                        }
                        val db = DatabaseQueries(this)
                        val res=db.availableStock(sno,name,stock,order,vendor,price,total)
                        Log.e("Result",res.toString())
                    }
                    rowno++
                }

            }
            else if (id==2) {
                while (rowIter.hasNext()) {

                    val myRow = rowIter.next() as HSSFRow
                    if (rowno != 0) {
                        val cellIter = myRow.cellIterator()
                        var colno = 0
                        var col1 = ""
                        var col2 = ""
                        var col3 = ""
                        var col4 = ""
                        var col5 = ""
                        var col6 = ""
                        var col7 = ""
                        var col8 = ""
                        var col9 = ""
                        var col10 = ""
                        var col11 = ""
                        var col12 = ""
                        var col13 = ""

                        while (cellIter.hasNext()) {
                            val myCell = cellIter.next() as HSSFCell
                            if (colno == 0) {
                                col1 = myCell.toString()
                            } else if (colno == 1) {
                                col2 = myCell.toString()
                            } else if (colno == 2) {
                                col3 = myCell.toString()
                            } else if (colno == 3) {
                                col4 = myCell.toString()
                            } else if (colno == 4) {
                                col5 = myCell.toString()
                            } else if (colno == 5) {
                                col6 = myCell.toString()
                            } else if (colno == 6) {
                                col7 = myCell.toString()
                            } else if (colno == 7) {
                                col8 = myCell.toString()
                            } else if (colno == 8) {
                                col9 = myCell.toString()
                            } else if (colno == 9) {
                                col10 = myCell.toString()
                            } else if (colno == 10) {
                                col11 = myCell.toString()
                            } else if (colno == 11) {
                                col12 = myCell.toString()
                            } else if (colno == 12) {
                                col13 = myCell.toString()
                            }
                            colno++
                        //    Log.e("Data", " Index :" + myCell.columnIndex + " -- " + myCell.toString())
                        }
                        val db = DatabaseQueries(this)
                        val res = db.pbrInward(col1, col2, col3, col4, col5, col6, col7,
                            col8, col9, col10, col11, col12, col13)
                        Log.e("Result", res.toString())
                    }
                    rowno++
                }
            }
            else if (id == 3)
            {
                while (rowIter.hasNext()) {

                    val myRow = rowIter.next() as HSSFRow
                    if (rowno != 0) {
                        val cellIter = myRow.cellIterator()
                        var colno = 0
                        var col1 = ""
                        var col2 = ""
                        var col3 = ""
                        var col4 = ""
                        var col5 = ""
                        var col6 = ""
                        var col7 = ""
                        var col8 = ""
                        var col9 = ""
                        while (cellIter.hasNext()) {
                            val myCell = cellIter.next() as HSSFCell
                            if (colno == 0) {
                                col1 = myCell.toString()
                            } else if (colno == 1) {
                                col2 = myCell.toString()
                            } else if (colno == 2) {
                                col3 = myCell.toString()
                            } else if (colno == 3) {
                                col4 = myCell.toString()
                            } else if (colno == 4) {
                                col5 = myCell.toString()
                            } else if (colno == 5) {
                                col6 = myCell.toString()
                            } else if (colno == 6) {
                                col7 = myCell.toString()
                            } else if (colno == 7) {
                                col8 = myCell.toString()
                            } else if (colno == 8) {
                                col9 = myCell.toString()
                            }
                            colno++
                        //    Log.e("Data", " Index :" + myCell.columnIndex + " -- " + myCell.toString())
                        }
                        val db = DatabaseQueries(this)
                        val res=db.pbrOutward(col1, col2, col3, col4, col5, col6, col7,
                            col8, col9)
                        Log.e("Result", res.toString())

                    }
                    rowno++
                }

            }
            else if (id == 4)
            {
                while (rowIter.hasNext()) {

                    val myRow = rowIter.next() as HSSFRow
                    if (rowno != 0) {
                        val cellIter = myRow.cellIterator()
                        var colno = 0
                        var col1 = ""
                        var col2 = ""
                        var col3 = ""
                        var col4 = ""
                        while (cellIter.hasNext()) {
                            val myCell = cellIter.next() as HSSFCell
                            if (colno == 0) {
                                col1 = myCell.toString()
                            } else if (colno == 1) {
                                col2 = myCell.toString()
                            } else if (colno == 2) {
                                col3 = myCell.toString()
                            } else if (colno == 3) {
                                col4 = myCell.toString()
                            }
                            colno++
                           // Log.e("Data", " Index :" + myCell.columnIndex + " -- " + myCell.toString())
                        }
                        val db = DatabaseQueries(this)
                        val res=db.monitoring(col1,col2,col3,col4)
                        Log.e("Result",res.toString())
                    }
                    rowno++
                }

            }
            else if (id == 5)
            {
                while (rowIter.hasNext()) {

                    val myRow = rowIter.next() as HSSFRow
                    if (rowno != 0) {
                        val cellIter = myRow.cellIterator()
                        var colno = 0
                        var col1 = ""
                        var col2 = ""
                        var col3 = ""
                        var col4 = ""
                        var col5 = ""
                        var col6 = ""
                        var col7 = ""
                        var col8 = ""
                        var col9 = ""
                        var col10 = ""
                        var col11 = ""
                        var col12 = ""
                        var col13 = ""
                        while (cellIter.hasNext()) {
                            val myCell = cellIter.next() as HSSFCell
                            if (colno == 0) {
                                col1 = myCell.toString()
                            } else if (colno == 1) {
                                col2 = myCell.toString()
                            } else if (colno == 2) {
                                col3 = myCell.toString()
                            } else if (colno == 3) {
                                col4 = myCell.toString()
                            } else if (colno == 4) {
                                col5 = myCell.toString()
                            } else if (colno == 5) {
                                col6 = myCell.toString()
                            } else if (colno == 6) {
                                col7 = myCell.toString()
                            } else if (colno == 7) {
                                col8 = myCell.toString()
                            } else if (colno == 8) {
                                col9 = myCell.toString()
                            } else if (colno == 9) {
                                col10 = myCell.toString()
                            } else if (colno == 10) {
                                col11 = myCell.toString()
                            } else if (colno == 11) {
                                col12 = myCell.toString()
                            } else if (colno == 12) {
                                col13 = myCell.toString()
                            }
                            colno++
                            //Log.e("Data", " Index :" + myCell.columnIndex + " -- " + myCell.toString())
                        }
                        val db = DatabaseQueries(this)
                        val res=db.sgConnections(col1, col2, col3, col4, col5, col6, col7,
                            col8, col9, col10, col11, col12, col13)
                        Log.e("Result",res.toString())
                    }
                    rowno++
                }
            }
        } catch (e: Exception) {
            Log.e("Import Excel", "error $e")
        }
    }
}
