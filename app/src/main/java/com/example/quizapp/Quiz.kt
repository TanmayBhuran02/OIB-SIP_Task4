package com.example.quizapp

data class Quiz(val questions: List<Question>, var currentQuestionIndex: Int, var score: Int)
