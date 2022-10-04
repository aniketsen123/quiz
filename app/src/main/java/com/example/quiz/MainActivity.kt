package com.example.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button=findViewById<Button>(R.id.start)
        val name=findViewById<EditText>(R.id.name)
        button.setOnClickListener {
            if (name.text.isEmpty()) {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            }
         else
            {
                val intent=Intent(this,QuizQuestions::class.java)
                intent.putExtra(Constants.User_name,name.text.toString())
                startActivity(intent)
            }
        }
    }
}