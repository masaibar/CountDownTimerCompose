package com.masaibar.countdowntimercompose.ui.timer

import android.os.CountDownTimer
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

    private val timer = object : CountDownTimer(
        calculateMillisUntilEndOfYear(now),
        1000
    ) {
        override fun onTick(millisUntilFinished: Long) {
            _uiState.update {
                it.copy(
                    remainSec = (millisUntilFinished / 1000).toInt()
                )
            }
        }

        override fun onFinish() {
            _uiState.update {
                it.copy(
                    remainSec = 0
                )
            }
        }
    }

    init {
        timer.start()
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

    fun calculateMillisUntilEndOfYear(now: Calendar): Long {
        val endOfYear = Calendar.getInstance().apply {
            set(Calendar.MONTH, Calendar.DECEMBER)
            set(Calendar.DAY_OF_MONTH, 31)
            set(Calendar.HOUR_OF_DAY, 23)
            set(Calendar.MINUTE, 59)
            set(Calendar.SECOND, 59)
            set(Calendar.MILLISECOND, 999)
        }

        // 現在時刻との差を計算し、ミリ秒単位で返す
        return endOfYear.timeInMillis - now.timeInMillis
    }
}
