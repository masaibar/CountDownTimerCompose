package com.masaibar.countdowntimercompose.ui.timer

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Calendar

data class UiState(
    val currentYear: Int,
    private val remainSec: Int
) {
    companion object {
        fun initialValue(now: Calendar): UiState = UiState(
            currentYear = now.get(Calendar.YEAR),
            remainSec = 0
        )
    }

    fun formatRemainingTime(): String {
        val days = remainSec / (24 * 60 * 60)
        val hours = (remainSec % (24 * 60 * 60)) / (60 * 60)
        val minutes = (remainSec % (60 * 60)) / 60
        val secs = remainSec % 60

        return buildString {
            if (days > 0) append("${days}日 ")
            if (hours > 0) append("${hours}時間 ")
            if (minutes > 0) append("${minutes}分 ")
            append("${secs}秒")
        }.trim()
    }
}

class CountDownTimerViewModel(
    now: Calendar = Calendar.getInstance()
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState.initialValue(now))
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val timer = YearEndCountDownTimer(now) {
        _uiState.update { state ->
            state.copy(remainSec = it)
        }
    }.start()

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }
}
