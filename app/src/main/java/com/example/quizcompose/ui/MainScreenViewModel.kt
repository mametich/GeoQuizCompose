package com.example.quizcompose.ui

import androidx.lifecycle.ViewModel
import com.example.quizcompose.R
import com.example.quizcompose.data.Question
import com.example.quizcompose.data.questionBank
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainScreenViewModel : ViewModel() {

    private val _quizUiState = MutableStateFlow(QuizUiState())
    val quizUiState: StateFlow<QuizUiState> = _quizUiState.asStateFlow()

    private var currentIndex = 0

    init {
        takeFirstQuestion()
    }

    private fun takeFirstQuestion() {
        val questionText = questionBank[currentIndex].textQuestion
        _quizUiState.update { currentState ->
            currentState.copy(
                questionText = questionText
            )
        }
    }

    fun nextQuestion() {
        currentIndex = (currentIndex + 1) % questionBank.size
        val nextQuestion = questionBank[currentIndex].textQuestion
        _quizUiState.update { currentState ->
            currentState.copy(
                questionText = nextQuestion
            )
        }
    }


    fun previousQuestion() {
        currentIndex = (currentIndex + questionBank.size - 1) % questionBank.size
        val previousQuestion = questionBank[currentIndex].textQuestion
        _quizUiState.update { currentState ->
            currentState.copy(
                questionText = previousQuestion
            )
        }
    }

    fun checkAnswer(userAnswer: Boolean): Int {
        return if (userAnswer == questionBank[currentIndex].answer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
    }
}