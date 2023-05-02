@file:Suppress("NOTHING_TO_INLINE", "unused")
package org.vorpal.research.kthelper.assert

import org.vorpal.research.kthelper.KtException
import kotlin.system.exitProcess

class UnreachableException(message: String) : KtException(message)
class AssertionException(message: String) : KtException(message) {
    constructor() : this("")
}

inline fun <T> asserted(condition: Boolean, action: () -> T): T {
    ktassert(condition)
    return action()
}

inline fun <T> asserted(condition: Boolean, message: String, action: () -> T): T {
    ktassert(condition, message)
    return action()
}

inline fun ktassert(cond: Boolean) = if (!cond) throw AssertionException() else {}

inline fun ktassert(cond: Boolean, message: String) = if (!cond) throw AssertionException(
    message
) else {}
inline fun ktassert(cond: Boolean, action: () -> Unit) = if (!cond) {
    action()
    throw AssertionException()
} else {}

inline fun <T> unreachable(message: String): T = fail(message)
inline fun <T> unreachable(noinline lazyMessage: () -> Any) =
    fail<T>(lazyMessage)

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
