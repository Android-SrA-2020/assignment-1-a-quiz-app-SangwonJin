package com.example.quizapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    private val questionBank = listOf(
        Question(R.string.question_1, false),
        Question(R.string.question_2, true),
        Question(R.string.question_3, true),
        Question(R.string.question_4, false),
        Question(R.string.question_5, false),
        Question(R.string.question_6, true),
        Question(R.string.question_7, false),
        Question(R.string.question_8, true),
        Question(R.string.question_9, false),
        Question(R.string.question_10, false),
        Question(R.string.question_11, false),
        Question(R.string.question_12, true),
        Question(R.string.question_13, false),
        Question(R.string.question_14, true),
        Question(R.string.question_15, false),
        Question(R.string.question_16, false),
        Question(R.string.question_17, true),
        Question(R.string.question_18, false),
        Question(R.string.question_19, false),
        Question(R.string.question_20, true)
    )

    private var questionIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        updateView()

        findViewById<ImageView>(R.id.next_imagebutton).setOnClickListener{next()}
        findViewById<ImageView>(R.id.previous_imagebutton).setOnClickListener{previous()}
        findViewById<Button>(R.id.true_button).setOnClickListener{answer(true)}
        findViewById<Button>(R.id.false_button).setOnClickListener{answer(false)}
    }

    //Go next question
    private fun next(){
        questionIndex = (questionIndex + 1) % 20
        updateView()
    }
    //Go previous question
    private fun previous(){
        if(questionIndex==0)
            questionIndex=20-1
        else
            questionIndex-=1
        updateView()
    }

    //Check the answer
    private fun answer(ans:Boolean){
        if(questionBank[questionIndex].answer == ans) {
            showPopup("Correct")
            next()
        }else{
            showPopup("Incorrect")
        }
    }

    //Show the question based on the index of questionBank
    private fun updateView() {
        findViewById<TextView>(R.id.question_text).setText(questionBank[questionIndex].resoruceId)
    }

    //Show popup dialog
    private fun showPopup(message: String){
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE)as LayoutInflater
        val view = inflater.inflate(R.layout.alert_popup,null)
        val textView: TextView = view.findViewById(R.id.textView)
        textView.setText(message)

        var alerDialog = AlertDialog.Builder(this)
            .setTitle("The result")
            .setNeutralButton("OK",null)
            .create()
        alerDialog.setView(view)
        alerDialog.show()
    }

}
