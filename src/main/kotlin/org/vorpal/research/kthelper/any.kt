@file:Suppress("unused")

package org.vorpal.research.kthelper

inline fun <T> T.runIf(cond: Boolean, crossinline block: T.() -> Unit) {
    if (cond) block()
}

inline fun <T, R> T.runIf(cond: Boolean, default: R, crossinline block: T.() -> R): R {
    return if (cond) block() else default
}
