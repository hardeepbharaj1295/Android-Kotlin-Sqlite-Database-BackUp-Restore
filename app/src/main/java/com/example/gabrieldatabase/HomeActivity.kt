package com.example.gabrieldatabase

import android.Manifest
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.example.gabrieldatabase.preferences.SharedPref
import com.example.gabrieldatabase.projectDatabase.DatabaseCreation
import java.io.File

class HomeActivity : AppCompatActivity() {

    private lateinit var shared: SharedPref
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        shared = SharedPref(this)
        permissions()
    }

    fun items(v:View)
    {
        startActivity(Intent(this,ProductsMainActivity::class.java))
        finish()
    }

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

    fun logout(v:View)
    {
        shared.setEmail("")
        startActivity(Intent(this,LoginActivity::class.java))
        finish()
    }

    fun importExcel(v:View)
    {
        startActivity(Intent(this,ImportExcelActivity::class.java))
        finish()
    }

    fun importData(view: View) {
        performRestore(db)
    }

    fun export(view: View) {
        val outFileName =
            Environment.getExternalStorageDirectory().toString() + File.separator + resources.getString(R.string.app_name) + File.separator
        Log.e("PAth", outFileName)
        performBackup(db, outFileName)
    }

    internal var db = DatabaseCreation(this)

    //ask to the user a name for the backup and perform it. The backup will be saved to a custom folder.
    private fun performBackup(db: DatabaseCreation, outFileName: String) {

        verifyStoragePermissions(this)

        val folder =
            File(Environment.getExternalStorageDirectory().toString() + File.separator + resources.getString(R.string.app_name))

        var success = true
        if (!folder.exists())
            success = folder.mkdirs()
        if (success) {

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Backup Name")
            val input = EditText(this)
            input.inputType = InputType.TYPE_CLASS_TEXT
            builder.setView(input)
            builder.setPositiveButton("Save", DialogInterface.OnClickListener { dialog, which ->
                val m_Text = input.text.toString()
                val out = "$outFileName$m_Text.db"
                db.backup(out)
            })
            builder.setNegativeButton("Cancel",
                DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

            builder.show()
        } else
            Toast.makeText(this, "Unable to create directory. Retry", Toast.LENGTH_SHORT).show()
    }

    //ask to the user what backup to restore
    private fun performRestore(db: DatabaseCreation) {

        verifyStoragePermissions(this)

        val folder =
            File(Environment.getExternalStorageDirectory().toString() + File.separator + resources.getString(R.string.app_name))
        if (folder.exists()) {

            val files = folder.listFiles()

            val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.select_dialog_item)
            for (file in files)
                arrayAdapter.add(file.name)

            val builderSingle = AlertDialog.Builder(this)
            builderSingle.setTitle("Restore:")
            builderSingle.setNegativeButton(
                "cancel",
                DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
            builderSingle.setAdapter(
                arrayAdapter,
                DialogInterface.OnClickListener { dialog, which ->
                    try {
                        db.importDB(files[which].path)
                    } catch (e: Exception) {
                        Toast.makeText(this@HomeActivity, "Unable to restore. Retry", Toast.LENGTH_SHORT).show()
                    }
                })
            builderSingle.show()
        } else
            Toast.makeText(this, "Backup folder not present.\nDo a backup before a restore!", Toast.LENGTH_SHORT).show()
    }

    private val REQUEST_EXTERNAL_STORAGE = 1
    private val PERMISSIONS_STORAGE =
        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    //check permissions.
    fun verifyStoragePermissions(activity: Activity) {
        // Check if we have read or write permission
        val writePermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val readPermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)

        if (writePermission != PackageManager.PERMISSION_GRANTED || readPermission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                activity,
                PERMISSIONS_STORAGE,
                REQUEST_EXTERNAL_STORAGE
            )
        }
    }
}
