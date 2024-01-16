package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast

class QuizActivity : AppCompatActivity() {

    private lateinit var questionTextView: TextView
    private lateinit var choicesRadioGroup: RadioGroup
    private lateinit var nextButton: Button

    private val quiz = Quiz(
        listOf(
            Question("Number of primitive data types in Java are?", listOf("6", "7", "8"), 2),
            Question("What is the size of float and double in java?", listOf("32 and 64", "32 and 32", "64 and 64"), 0),
            Question("Automatic type conversion is possible in which of the possible cases?", listOf("Byte to Int", "Int to Long", "Long to Int"), 1),
            Question("Select the valid statement.", listOf("char[] ch = new char(5)", "char[] ch = new char()", "char[] ch = new char[5]"), 2),
            Question("When an array is passed to a method, what does the method receive?", listOf("Reference of array", "Copy of array", "Copy of first element"), 0),
            Question("Arrays in java are-", listOf("Object Reference", "Objects", "Primitive Datatypes"), 1),
            Question("When is the object created with new keyword?", listOf("At run time", "At compile time", "Depends of code"), 0),
            Question("In which of the following is toString() method defined?", listOf("java.lang.Object", "java.lang.String", "java.lang.Util"), 0),
            Question("compareTo() returns", listOf("True", "False", "An Int value"), 2),
            Question("To which of the following does the class string belong to.", listOf("java.lang", "java.applet", "java.awt"), 0)
        ),
        currentQuestionIndex = 0,
        score = 0
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        questionTextView = findViewById(R.id.questionTextView)
        choicesRadioGroup = findViewById(R.id.choicesRadioGroup)
        nextButton = findViewById(R.id.nextButton)

        showQuestion()
    }

    fun nextQuestion(view: View) {
        val selectedRadioButton = findViewById<RadioButton>(choicesRadioGroup.checkedRadioButtonId)

        if (selectedRadioButton != null) {
            val selectedAnswerIndex = choicesRadioGroup.indexOfChild(selectedRadioButton)

            if (selectedAnswerIndex == quiz.questions[quiz.currentQuestionIndex].correctAnswer) {
                quiz.score++
            }

            quiz.currentQuestionIndex++

            if (quiz.currentQuestionIndex < quiz.questions.size) {
                showQuestion()
            } else {
                showScore()
            }

            choicesRadioGroup.clearCheck()
        } else {
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showQuestion() {
        val currentQuestion = quiz.questions[quiz.currentQuestionIndex]

        questionTextView.text = currentQuestion.text

        choicesRadioGroup.removeAllViews()

        for (i in currentQuestion.choices.indices) {
            val radioButton = RadioButton(this)
            radioButton.text = currentQuestion.choices[i]
            choicesRadioGroup.addView(radioButton)
        }
    }

    private fun showScore() {
        val intent = Intent(this, ScoreActivity::class.java)
        intent.putExtra("SCORE", quiz.score)
        startActivity(intent)
        finish()
    }
}
