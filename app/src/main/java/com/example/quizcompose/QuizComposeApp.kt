package com.example.quizcompose

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.quizcompose.ui.CheatScreen
import com.example.quizcompose.ui.MainScreen
import com.example.quizcompose.ui.MainScreenViewModel



sealed class Screen(val route: String) {
    data object MainScreen : Screen(route = "main_screen")
    data object CheatScreen : Screen(route = "cheat_screen/{answer}")
}

@Composable
fun QuizComposeApp(
    mainScreenViewModel: MainScreenViewModel = viewModel(),
    navHostController: NavHostController = rememberNavController()
) {
    val quizUiState by mainScreenViewModel.quizUiState.collectAsState()
    val context = LocalContext.current

    NavHost(
        navController = navHostController,
        startDestination = Screen.MainScreen.route,
    ) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(
                questionText = quizUiState.questionText,
                onClickTrueButton = {
                    Toast.makeText(
                        context,
                        mainScreenViewModel.checkAnswer(true),
                        Toast.LENGTH_SHORT
                    ).show()
                },
                onClickFalseButton = {
                    Toast.makeText(
                        context,
                        mainScreenViewModel.checkAnswer(false),
                        Toast.LENGTH_SHORT
                    ).show()
                },
                onClickNextQuestionButton = { mainScreenViewModel.nextQuestion() },
                onClickPreviousQuestionButton = { mainScreenViewModel.previousQuestion() },
                onClickToCheatScreen = { navHostController.navigate(Screen.CheatScreen.route) },
            )
        }
        composable(
            route = Screen.CheatScreen.route,
            arguments = listOf(navArgument("answer") {
                type = NavType.BoolType
            })
        ) {
            CheatScreen(
                warningText = stringResource(R.string.warning_text),
                answer = "TRUE".uppercase(),
                onClickButtonShowAnswer = {  },
            )
        }
    }
}
