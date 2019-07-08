package com.example.gabrieldatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.gabrieldatabase.preferences.SharedPref
import com.example.gabrieldatabase.projectDatabase.DatabaseQueries

class LoginActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var sharedPref: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email = findViewById(R.id.email)
        password = findViewById(R.id.password)

        sharedPref = SharedPref(this)
        if (!sharedPref.getEmail().equals(""))
        {
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }
    }

    fun goToLogin(v: View)
    {
        val db = DatabaseQueries(this)
        val res = db.login(email.text.toString(),password.text.toString())
        if (res.moveToFirst())
        {
            sharedPref.setEmail(email.text.toString())

            Toast.makeText(this,"Login",Toast.LENGTH_LONG).show()
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }
        else
        {
            Toast.makeText(this,"wrong",Toast.LENGTH_LONG).show()
        }
    }

    fun goToRegister(v: View)
    {
        startActivity(Intent(this,SignUpActivity::class.java))
        finish()
    }
}
