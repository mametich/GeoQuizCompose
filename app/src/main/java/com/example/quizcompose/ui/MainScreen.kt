package com.example.quizcompose.ui

import android.graphics.drawable.Icon
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quizcompose.R

@Composable
fun QuizComposeApp(
    mainScreenViewModel: MainScreenViewModel = viewModel()
) {
    val quizUiState by mainScreenViewModel.quizUiState.collectAsState()
    val context = LocalContext.current
    TextWithButtons(
        questionText = quizUiState.questionText,
        onClickTrueButton = {
            Toast.makeText(
                context,
                mainScreenViewModel.checkAnswer(true),
                Toast.LENGTH_SHORT)
                .show()
        },
        onClickFalseButton = {
            Toast.makeText(
                context,
                mainScreenViewModel.checkAnswer(false),
                Toast.LENGTH_SHORT)
                .show()
        },
        onClickPreviousQuestionButton = { mainScreenViewModel.previousQuestion() },
        onClickNextQuestionButton = { mainScreenViewModel.nextQuestion() },
        onClickToCheatScreen = { },
        modifier = Modifier
    )
}

@Composable
fun TextWithButtons(
    questionText: String,
    onClickTrueButton: () -> Unit,
    onClickFalseButton: () -> Unit,
    onClickNextQuestionButton: () -> Unit,
    onClickPreviousQuestionButton: () -> Unit,
    onClickToCheatScreen: () -> Unit,
    modifier: Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Text(
            text = questionText,
            fontSize = 20.sp,
            modifier = Modifier.padding(24.dp)
        )
        Row {
            Button(
                onClick = onClickTrueButton,
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Green,
                    contentColor = Color.Black
                )
            ) {
                Text(
                    text = stringResource(R.string.true_button).uppercase(),
                    fontSize = 18.sp,
                )
            }
            Button(
                onClick = onClickFalseButton,
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                )
            ) {
                Text(
                    text = stringResource(R.string.false_button).uppercase(),
                    fontSize = 18.sp
                )
            }
        }
        Row {
            Button(
                onClick = onClickPreviousQuestionButton,
                shape = RoundedCornerShape(16.dp),
            ) {
                Row(horizontalArrangement = Arrangement.SpaceAround) {
                    Icon(
                        Icons.AutoMirrored.Default.KeyboardArrowLeft,
                        contentDescription = "Previous Question"
                    )
                    Text(
                        text = stringResource(R.string.prev_button).uppercase(),
                        fontSize = 18.sp,
                    )
                }
            }
            Button(
                onClick = onClickNextQuestionButton,
                shape = RoundedCornerShape(16.dp),
            ) {
                Row {
                    Text(
                        text = stringResource(R.string.next_button).uppercase(),
                        fontSize = 18.sp,
                    )
                    Icon(
                        Icons.AutoMirrored.Default.KeyboardArrowRight,
                        contentDescription = "Next Question"
                    )
                }
            }
        }
        Button(
            onClick = onClickToCheatScreen,
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = stringResource(R.string.cheat_button).uppercase(),
                fontSize = 18.sp
            )
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun QuizComposeAppPreview() {
    TextWithButtons(
        questionText = "Canberra is the capital of Australia.",
        onClickTrueButton = {},
        onClickFalseButton = {},
        onClickPreviousQuestionButton = {},
        onClickNextQuestionButton = {},
        onClickToCheatScreen = {},
        modifier = Modifier
    )
}
