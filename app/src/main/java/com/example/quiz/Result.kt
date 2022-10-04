package com.example.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.io.BufferedReader

class Result : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        var tvname:TextView?=null
        tvname=findViewById(R.id.textView3)
        val score:TextView=findViewById(R.id.textView4)
        val button=findViewById<Button>(R.id.btn_submit)
        tvname.text=intent.getStringExtra(Constants.User_name)
        val questions:Int=intent.getIntExtra(Constants.total_question,0)
        val correct:Int=intent.getIntExtra(Constants.correct_ans,0)
        score.text="Your Score is ${correct} in ${questions}"
        button.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}