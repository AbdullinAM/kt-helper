package org.jetbrains.research.kthelper.time

import org.jetbrains.research.kthelper.assert.asserted
import org.jetbrains.research.kthelper.assert.ktassert
import kotlin.system.measureTimeMillis

@Deprecated("use builtin Kotlin methods")
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

@Deprecated(
    message = "use builtin Kotlin methods",
    replaceWith = ReplaceWith("measureTimeMillis(block = action)", "kotlin.system.measureTimeMillis")
)
fun timed(action: () -> Unit): Long = measureTimeMillis(action)

fun <T> measureTimeMillis(action: () -> T): Pair<Long, T> {
    var res: T
    val time = measureTimeMillis {
        res = action()
    }
    return time to res
}