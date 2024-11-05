package com.example.quizcompose.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizcompose.R

@Composable
fun CheatScreen(
    isTextVisible: Boolean,
    warningText: String,
    answerText: String,
    onClickButtonShowAnswer: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Text(
            text = warningText,
            fontSize = 24.sp,
            modifier = Modifier.padding(24.dp),
            textAlign = TextAlign.Center
        )
        if (isTextVisible) {
            Text(
                text = answerText,
                fontSize = 18.sp,
                modifier = Modifier.padding(24.dp)
            )
        }
        Button(
            onClick = onClickButtonShowAnswer
        ) {
            Text(
                text = stringResource(R.string.show_answer_button).uppercase(),
                fontSize = 18.sp
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CheatsTextAndButtonPreview() {
    CheatScreen(
        warningText = stringResource(R.string.warning_text),
        onClickButtonShowAnswer = { },
        answerText = "0",
        isTextVisible = false
    )
}