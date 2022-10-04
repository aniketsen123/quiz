package com.example.quiz

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestions : AppCompatActivity(), View.OnClickListener {
    private var musername:String?=null
    private var mcurrentposition=1
    private var mquestionlist:ArrayList<Question>?=null
    private var mselectedposition=0
    private var progressbar:ProgressBar?=null
    private var tvprogress:TextView?=null
    private var tvquestion:TextView?=null
    private var image:ImageView?=null
    private var optionone:TextView?=null
    private var optiontwo:TextView?=null
    private var optionthree:TextView?=null
    private var optionfour:TextView?=null
    private var button:Button?=null
    private var CorrecAnswer:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)
        musername=intent.getStringExtra(Constants.User_name)
         progressbar=findViewById(R.id.progressBar)
        tvprogress=findViewById(R.id.tv_progress)
        tvquestion=findViewById(R.id.question)
        image=findViewById(R.id.image)
        optionone=findViewById(R.id.tv_option_one)
        optiontwo=findViewById(R.id.tv_option_two)
        optionthree=findViewById(R.id.tv_option_three)
        optionfour=findViewById(R.id.tv_option_four)
        mquestionlist = Constants.getQuestions()
        button=findViewById(R.id.btn_submit)
        optionone?.setOnClickListener(this)
        optiontwo?.setOnClickListener(this)
        optionthree?.setOnClickListener(this)
        optionfour?.setOnClickListener(this)
        button?.setOnClickListener(this)
        setQuestion()
    }

    @SuppressLint("SetTextI18n")
    private fun setQuestion() {
        optionview()
        val question: Question = mquestionlist!![mcurrentposition-1]
        progressbar?.progress = mcurrentposition
        tvprogress?.text = "${mcurrentposition}/${progressbar?.max}"
        tvquestion?.text = question.question
        image?.setImageResource(question.img)
        optionone?.text = question.optionOne
        optiontwo?.text = question.optionTwo
        optionthree?.text = question.optionThree
        optionfour?.text = question.optionFour


        if(mcurrentposition==mquestionlist!!.size)
        {
            button?.text="FINISH"
        }
        else
            button?.text="SUBMIT"
    }
//So a function to set the default options view when the new position is loaded or when the answer is
//
//re selected.
//
//So it's going to reset basically this the colors of the selected answers and then we can just set it
//
//to be gray.
    private fun optionview()
{
        //val nullableName4: String? = null
    //
    //    nullableName4?.let { println(it.toLowerCase()) }
        val option=ArrayList<TextView>()
    optionone?.let {
        option.add(0,it)
    }
    optiontwo?.let {
        option.add(1,it)
    }
    optionthree?.let {
        option.add(2,it)
    }
    optionfour?.let {
        option.add(3,it)
    }
    for(option1 in option)
    {
        option1.setTextColor(Color.parseColor("#7A8089"))
        option1.typeface= Typeface.DEFAULT
        option1.background=ContextCompat.getDrawable(this,R.drawable.default_option_border_bg)
    }
    }
    private fun selectedoption(tv:TextView,selectedoption:Int)
    {
        optionview()
        mselectedposition=selectedoption
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background=ContextCompat.getDrawable(this,R.drawable.selected_option_border_bg)
    }
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tv_option_one -> {
                optionone?.let {
                    selectedoption(it, 1)
                }
            }
            R.id.tv_option_two -> {
                optiontwo?.let {
                    selectedoption(it, 2)
                }
            }
            R.id.tv_option_three -> {
                optionthree?.let {
                    selectedoption(it, 3)
                }
            }
            R.id.tv_option_four -> {
                optionfour?.let {
                    selectedoption(it, 4)
                }
            }

            R.id.btn_submit -> {
                if (mselectedposition == 0) {
                       mcurrentposition++
                    if(mcurrentposition<=mquestionlist!!.size)
                    {
                        setQuestion()
                    }
                    else
                    {
                        Toast.makeText(this,"quiz successfully completed",Toast.LENGTH_SHORT).show()
                        val intent=Intent(this,Result::class.java)
                        intent.putExtra(Constants.User_name,musername)
                        intent.putExtra(Constants.correct_ans, CorrecAnswer)
                       intent.putExtra(Constants.total_question,mquestionlist?.size)
                        startActivity(intent)
                        finish()
                    }
                }
                else
                {
                    val question=mquestionlist?.get(mcurrentposition-1)
                    if(mselectedposition!=question!!.correctAnswer)
                    {
                        answer(mselectedposition,R.drawable.wrong_option_border_bg)
                    }
                    else
                    {answer(mselectedposition,R.drawable.correct_option_border_bg)
                    CorrecAnswer++
                    }
                    answer(question!!.correctAnswer,R.drawable.correct_option_border_bg)
                    if(mcurrentposition==mquestionlist!!.size)
                        button?.setText("Finish")
                    else
                        button?.setText("GO TO NEXT QUESTION")
                    mselectedposition=0
                }
            }
        }
    }
        private fun answer(answer:Int ,drawableview:Int)
        {
                   when(answer)
                   {
                       1->{
                           optionone?.background=ContextCompat.getDrawable(
                               this,drawableview
                           )
                       }
                       2->{
                           optiontwo?.background=ContextCompat.getDrawable(
                               this,drawableview
                           )
                       }
                       3->{
                           optionthree?.background=ContextCompat.getDrawable(
                               this,drawableview
                           )
                       }
                       4->{
                           optionfour?.background=ContextCompat.getDrawable(
                               this,drawableview
                           )
                       }
                   }
        }
    }
