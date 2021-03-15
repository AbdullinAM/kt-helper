package org.jetbrains.research.kthelper.time

import org.jetbrains.research.kthelper.assert.asserted
import org.jetbrains.research.kthelper.assert.ktassert

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

fun <T> timed(action: () -> T): Pair<Long, T> {
    val timer = Timer()
    val result = action()
    return timer.stop() to result
}