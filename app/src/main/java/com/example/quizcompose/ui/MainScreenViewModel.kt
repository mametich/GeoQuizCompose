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

    fun showVisibility() {
        if (_quizUiState.value.isVisible) {
            _quizUiState.update { currentState ->
                currentState.copy(
                    isVisible = false
                )
            }
        } else {
            _quizUiState.update { currentState ->
                currentState.copy(
                    isVisible = true
                )
            }
        }
    }

    private fun takeFirstQuestion() {
        _quizUiState.update { currentState ->
            currentState.copy(
                questionText = questionBank[currentIndex].textQuestion,
                answerText = questionBank[currentIndex].answer.toString().uppercase()
            )
        }
    }

    fun nextQuestion() {
        currentIndex = (currentIndex + 1) % questionBank.size
        _quizUiState.update { currentState ->
            currentState.copy(
                questionText = questionBank[currentIndex].textQuestion,
                answerText = questionBank[currentIndex].answer.toString().uppercase()
            )
        }
    }

    fun previousQuestion() {
        currentIndex = (currentIndex + questionBank.size - 1) % questionBank.size
        _quizUiState.update { currentState ->
            currentState.copy(
                questionText = questionBank[currentIndex].textQuestion,
                answerText = questionBank[currentIndex].answer.toString().uppercase()
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