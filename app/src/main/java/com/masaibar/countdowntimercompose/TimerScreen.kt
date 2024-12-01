package com.masaibar.countdowntimercompose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.masaibar.countdowntimercompose.ui.theme.CountDownTimerComposeTheme
import com.masaibar.countdowntimercompose.ui.timer.CountDownTimerViewModel
import com.masaibar.countdowntimercompose.ui.timer.UiState

@Composable
fun TimerScreen(
    viewModel: CountDownTimerViewModel,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    TimerScreenContent(
        uiState
    )
}

@Composable
private fun TimerScreenContent(uiState: UiState) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text(
                text = "${uiState.currentYear}年が終わるまで\n" + uiState.formatRemainingTime(),
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}


@Preview(
    showBackground = true,
)
@Composable
private fun TimerContentPreview() {
    CountDownTimerComposeTheme {
        TimerScreenContent(
            UiState(
                2024,
                1000000
            )
        )
    }
}
