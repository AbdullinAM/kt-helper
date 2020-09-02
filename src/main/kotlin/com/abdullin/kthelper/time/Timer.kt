package com.abdullin.kthelper.time

import com.abdullin.kthelper.assert.asserted
import com.abdullin.kthelper.assert.ktassert

class Timer {
    private var startTime = System.currentTimeMillis()
    private var finishTime = System.currentTimeMillis()

    var isStarted = true
        private set
    val isFinished get() = !isStarted

    val current get() = asserted(isStarted) { System.currentTimeMillis() - startTime }

    fun start() {
        ktassert(isFinished)
        startTime = System.currentTimeMillis()
        isStarted = true
    }

    fun stop(): Long {
        ktassert(isStarted)
        finishTime = System.currentTimeMillis()
        isStarted = false
        return finishTime - startTime
    }
}

fun timed(action: () -> Unit): Long {
    val timer = Timer()
    action()
    return timer.stop()
}