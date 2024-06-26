@file:Suppress("unused")

package org.vorpal.research.kthelper

import org.vorpal.research.kthelper.assert.asserted

@Suppress("UNCHECKED_CAST", "MemberVisibilityCanBePrivate")
class Try<T> internal constructor(val unsafe: Any?) {
    @PublishedApi internal val failure: Failure? get() = unsafe as? Failure

    @PublishedApi internal data class Failure(val exception: Throwable)

    companion object {
        fun <T> just(value: T) = Try<T>(value)
        fun <T> exception(exception: Throwable) = Try<T>(
            Failure(exception)
        )
    }

    val isFailure: Boolean get() = unsafe is Failure
    val isSuccess: Boolean get() = !isFailure
    val exception: Throwable get() = asserted(isFailure) { failure!!.exception }

    fun getOrDefault(value: T) = when {
        isSuccess -> unsafe as T
        else -> value
    }

    inline fun getOrElse(block: (Throwable) -> T) = when {
        isSuccess -> unsafe as T
        else -> block(failure!!.exception)
    }

    inline fun orElse(block: (Throwable) -> Unit) {
        if (isFailure) block(exception)
    }

    fun getOrNull() = when {
        isSuccess -> unsafe as T
        else -> null
    }

    fun getOrThrow(): T = getOrThrow { it }

    inline fun getOrThrow(crossinline action: (Throwable) -> Throwable): T {
        failure?.apply {
            val throwable = action(exception)
            throw throwable
        }
        return unsafe as T
    }

    inline fun <K> map(action: (T) -> K) = when {
        isSuccess -> just(action(unsafe as T))
        else -> exception(failure!!.exception)
    }

    inline fun let(action: (T) -> Unit) = when {
        isSuccess -> action(unsafe as T)
        else -> {}
    }
}

inline fun <T> tryOrNull(action: () -> T): T? = `try`(action).getOrNull()

inline fun <T> safeTry(body: () -> T) = `try`(body)

inline fun <T> `try`(body: () -> T): Try<T> = try {
    Try.just(body())
} catch (e: Throwable) {
    Try.exception(e)
}
