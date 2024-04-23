package org.vorpal.research.kthelper

import java.util.concurrent.TimeUnit
import kotlin.time.Duration

fun buildProcess(command: List<String>, builderActions: ProcessBuilder.() -> Unit = {}): Process {
    val builder = ProcessBuilder(command)
    builder.builderActions()
    return builder.start()!!
}

fun executeProcess(command: List<String>, actions: Process.() -> Unit = {}) {
    val process = buildProcess(command.toList())
    process.actions()
    process.waitFor()
    process.destroyReliably()
}

fun executeProcessWithTimeout(command: List<String>, timeout: Duration, actions: Process.() -> Unit = {}) {
    val process = buildProcess(command.toList())
    process.actions()
    process.waitFor(timeout.inWholeMilliseconds, TimeUnit.MILLISECONDS)
    process.destroyReliably()
}

fun Process.destroyReliably() {
    this.destroy()
    if (this.isAlive) {
        this.destroyForcibly()
    }
}
