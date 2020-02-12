@file:Suppress("NOTHING_TO_INLINE")
package com.abdullin.kthelper.util

import kotlin.system.exitProcess

class UnreachableException(message: String) : Exception(message)

@Suppress("ControlFlowWithEmptyBody")
inline fun assert(cond: Boolean, message: String) = if (!cond) exit<Unit>(message) else {}
@Suppress("ControlFlowWithEmptyBody")
inline fun assert(cond: Boolean, action: () -> Unit) = if (!cond) action() else {}

inline fun <T> unreachable(message: String): T = fail(message)
inline fun <T> unreachable(noinline lazyMessage: () -> Any) = fail<T>(lazyMessage)

inline fun exit(message: String) = exit<Unit>(message)
inline fun exit(lazyMessage: () -> Any) = exit<Unit>(lazyMessage)

inline fun <T> exit(message: String): T = exit<T> { println(message) }
inline fun <T> exit(lazyMessage: () -> Any): T {
    lazyMessage()
    exitProcess(0)
}

fun <T> fail(message: String): T = error(message)
fun <T> fail(lazyMessage: () -> Any): T {
    lazyMessage()
    error("Failure")
}