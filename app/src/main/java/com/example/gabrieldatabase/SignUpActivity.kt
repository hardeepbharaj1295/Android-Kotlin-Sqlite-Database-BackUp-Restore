package com.example.gabrieldatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.gabrieldatabase.projectDatabase.DatabaseQueries

class SignUpActivity : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var number: EditText
    private lateinit var password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        name = findViewById(R.id.name)
        email = findViewById(R.id.email)
        number = findViewById(R.id.number)
        password = findViewById(R.id.password)

    }

    fun goToRegister(v:View)
    {
        val queries = DatabaseQueries(this)
        val result = queries.signUp(name.text.toString(),email.text.toString()
            ,number.text.toString().toLong(),password.text.toString())

        if (result>0)
        {
            Toast.makeText(this,"Registered",Toast.LENGTH_LONG).show()
        }
        else
        {
            Toast.makeText(this,"Not",Toast.LENGTH_LONG).show()
        }
    }

    fun gotoLogin(v:View)
    {
        startActivity(Intent(this,LoginActivity::class.java))
        finish()
    }
}
