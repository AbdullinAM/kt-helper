@file:Suppress("unused")

package org.vorpal.research.kthelper

import kotlinx.coroutines.yield
import java.io.File
import java.util.concurrent.TimeUnit
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

/**
 * Wrapper for ProcessBuilder
 */
fun buildProcess(vararg command: String, builderActions: ProcessBuilder.() -> Unit = {}): Process =
    buildProcess(command.toList(), builderActions)

fun buildProcess(command: List<String>, builderActions: ProcessBuilder.() -> Unit = {}): Process {
    val builder = ProcessBuilder(command)
    builder.builderActions()
    return builder.start()!!
}

/**
 * Execute given command in a separate process and suppress all output
 */
fun executeProcess(vararg command: String, actions: Process.() -> Unit = {}) =
    executeProcess(command.toList(), actions)

fun executeProcess(command: List<String>, actions: Process.() -> Unit = {}) {
    val process = buildProcess(command.toList()) {
        this.redirectErrorStream(true)
        this.redirectOutput(nullFile())
    }
    process.actions()
    process.waitFor()
    process.terminateOrKill(10U, waitTime = 500.milliseconds)
}


/**
 * Execute given command in a separate process and suppress all output
 */
fun executeProcessWithTimeout(command: List<String>, timeout: Duration, actions: Process.() -> Unit = {}) {
    val process = buildProcess(command.toList())
    process.actions()
    process.waitFor(timeout.inWholeMilliseconds, TimeUnit.MILLISECONDS)
    process.terminateOrKill(10U, waitTime = 500.milliseconds)
}

/**
 * Repeatedly try to destroy a process using `destroy` method.
 * `attempts` controls the maximum number of attempts, 0 means infinite
 * `waitTime` controls the waiting time between each attempt
 *
 * Return true if a process has been successfully terminated
 */
fun Process.terminate(
    attempts: UInt = 0U, // 0 means infinite
    waitTime: Duration = 1.seconds
): Boolean {
    var attemptCount = 0U
    while (this.isAlive && (attempts == 0U || attemptCount < attempts)) {
        this.destroy()
        ++attemptCount
        if (!this.isAlive) break
        Thread.sleep(waitTime.inWholeMilliseconds)
    }
    return !this.isAlive
}

/**
 * Repeatedly try to destroy a process using `destroy` method.
 * `attempts` controls the maximum number of attempts, 0 means infinite
 */
suspend fun Process.terminate(attempts: UInt): Boolean {
    var attemptCount = 0U
    while (this.isAlive && (attempts == 0U || attemptCount < attempts)) {
        this.destroy()
        ++attemptCount
        if (this.isAlive) yield()
    }
    return !this.isAlive
}

/**
 * Terminate or kill a process using `terminate` and `destroyForcibly` methods.
 * `attempts` controls the maximum number of attempts, 0 means infinite
 * `waitTime` controls the waiting time between each attempt
 */
fun Process.terminateOrKill(
    attempts: UInt,
    waitTime: Duration = 1.seconds
) {
    if (!this.terminate(attempts, waitTime)) {
        this.destroyForcibly()
    }
}

/**
 * Null destination for process output
 */
fun nullFile(): File = File(
    when {
        System.getProperty("os.name").startsWith("Windows") -> "NUL"
        else -> "/dev/null"
    }
)
