package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ScoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val scoreTextView = findViewById<TextView>(R.id.scoreTextView)

        val score = intent.getIntExtra("SCORE", 0)
        scoreTextView.text = "You Have Scored $score points"
    }
}
