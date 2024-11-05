package com.example.quizcompose

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.quizcompose.data.questionBank
import com.example.quizcompose.ui.CheatScreen
import com.example.quizcompose.ui.MainScreen
import com.example.quizcompose.ui.MainScreenViewModel

sealed class Screen(val route: String) {
    data object MainScreen : Screen(route = "main_screen")
    data object CheatScreen : Screen(route = "cheat_screen")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizComposeAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }
    )
}


@Composable
fun QuizComposeApp(
    mainScreenViewModel: MainScreenViewModel = viewModel(),
    navHostController: NavHostController = rememberNavController()
) {

    val backStackEntry by navHostController.currentBackStackEntryAsState()

    Scaffold(
        topBar = {
            QuizComposeAppBar(
                canNavigateBack = navHostController.previousBackStackEntry != null,
                navigateUp = { navHostController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val quizUiState by mainScreenViewModel.quizUiState.collectAsState()
        val context = LocalContext.current

        NavHost(
            navController = navHostController,
            startDestination = Screen.MainScreen.route,
            modifier = Modifier.padding(innerPadding)
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
                    onClickToCheatScreen = {
                        navHostController.navigate(Screen.CheatScreen.route)
                    },
                )
            }
            composable(
                route = Screen.CheatScreen.route,
            ) {
                CheatScreen(
                    warningText = stringResource(R.string.warning_text),
                    answerText = quizUiState.answerText,
                    isTextVisible = quizUiState.isVisible,
                    onClickButtonShowAnswer = {
                        mainScreenViewModel.showVisibility()
                    },
                )
            }
        }

    }











}

