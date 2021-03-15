package org.jetbrains.research.kthelper

import org.jetbrains.research.kthelper.assert.asserted

@Suppress("UNCHECKED_CAST")
class Try<T> internal constructor(@PublishedApi internal val unsafe: Any?) {
    @PublishedApi internal val failure: Failure? get() = unsafe as? Failure

    @PublishedApi internal data class Failure(val exception: Throwable)

    companion object {
        fun <T> just(value: T) = Try<T>(value)
        fun <T> exception(exception: Throwable) = Try<T>(
            Failure(exception)
        )
    }

    val isFailure get() = unsafe is Failure
    val isSuccess get() = !isFailure
    val exception get() = asserted<Throwable>(isFailure) { failure!!.exception }

    fun getOrDefault(value: T) = when {
        isSuccess -> unsafe as T
        else -> value
    }

    inline fun getOrElse(block: () -> T) = when {
        isSuccess -> unsafe as T
        else -> block()
    }

    fun getOrNull() = when {
        isSuccess -> unsafe as T
        else -> null
    }

    fun getOrThrow(): T {
        failure?.apply { throw exception }
        return unsafe as T
    }

    inline fun getOrThrow(action: () -> Unit): T {
        failure?.apply {
            action()
            throw exception
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