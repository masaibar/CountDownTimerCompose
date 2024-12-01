package com.masaibar.countdowntimercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CountDownTimerComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        Timer(
                            viewModel = CountDownTimerViewModel(),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Timer(
    viewModel: CountDownTimerViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    TimerContent(uiState, modifier)
}

@Composable
private fun TimerContent(
    uiState: UiState,
    modifier: Modifier
) {
    Text(
        text = "${uiState.currentYear}年が終わるまで\n" + uiState.formatRemainingTime(),
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}


@Preview(
    showBackground = true,
)
@Composable
private fun TimerContentPreview() {
    CountDownTimerComposeTheme {
        TimerContent(
            UiState(
                2024,
                1000000
            ),
            Modifier
        )
    }
}
