package com.masaibar.countdowntimercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.masaibar.countdowntimercompose.ui.theme.CountDownTimerComposeTheme
import com.masaibar.countdowntimercompose.ui.timer.CountDownTimerViewModel
import com.masaibar.countdowntimercompose.ui.timer.TimerScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CountDownTimerComposeTheme {
                TimerScreen(viewModel = CountDownTimerViewModel())
            }
        }
    }
}
