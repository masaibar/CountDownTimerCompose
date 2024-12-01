package com.masaibar.countdowntimercompose.ui.timer

import android.os.CountDownTimer
import java.util.Calendar

class YearEndCountDownTimer(
    now: Calendar,
    private val onRemainSecUpdated: (Int) -> Unit
) : CountDownTimer(now.calculateMillisUntilEndOfYear(), 100) {
    override fun onTick(millisUntilFinished: Long) {
        onRemainSecUpdated((millisUntilFinished / 1000).toInt())
    }

    override fun onFinish() {
        onRemainSecUpdated(0)
    }
}

private fun Calendar.calculateMillisUntilEndOfYear(): Long {
    val endOfYear = Calendar.getInstance().apply {
        set(Calendar.MONTH, Calendar.DECEMBER)
        set(Calendar.DAY_OF_MONTH, 31)
        set(Calendar.HOUR_OF_DAY, 23)
        set(Calendar.MINUTE, 59)
        set(Calendar.SECOND, 59)
        set(Calendar.MILLISECOND, 999)
    }

    // 現在時刻との差を計算し、ミリ秒単位で返す
    return endOfYear.timeInMillis - this.timeInMillis
}
