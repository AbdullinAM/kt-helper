package org.vorpal.research.kthelper

import java.io.File
import java.util.concurrent.TimeUnit
import kotlin.time.Duration

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
    process.destroyReliably()
}


/**
 * Execute given command in a separate process and suppress all output
 */
fun executeProcessWithTimeout(command: List<String>, timeout: Duration, actions: Process.() -> Unit = {}) {
    val process = buildProcess(command.toList())
    process.actions()
    process.waitFor(timeout.inWholeMilliseconds, TimeUnit.MILLISECONDS)
    process.destroyReliably()
}


/**
 * Destroy a process reliably using `destroy` and `destroyForcibly` methods
 */
fun Process.destroyReliably() {
    this.destroy()
    if (this.isAlive) {
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
