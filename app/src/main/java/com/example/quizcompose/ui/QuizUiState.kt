package com.example.quizcompose.ui

data class QuizUiState(
    val questionText: String = "",
    val answerIsTrue: Boolean = false,
    val answerText: String = "",
    val isVisible: Boolean = false
)

