@file:Suppress("DEPRECATION")

package org.vorpal.research.kthelper.collection

@Deprecated("Use built-in kotlin builders instead")
interface MutableBuilder<T> : MutableCollection<T> {
    val inner: MutableCollection<T>

    operator fun T.unaryPlus() {
        inner += this
    }

    operator fun Collection<T>.unaryPlus() {
        inner += this
    }
}

@Deprecated("Use built-in kotlin builders instead")
open class ListBuilder<T>(
    override val inner: MutableList<T> = mutableListOf()
) : MutableBuilder<T>, MutableList<T> by inner

@Deprecated("Use built-in kotlin builders instead")
open class SetBuilder<T>(
    override val inner: MutableSet<T> = mutableSetOf()
) : MutableBuilder<T>, MutableSet<T> by inner

@Deprecated("Use built-in kotlin builders instead")
fun <T> buildList(init: ListBuilder<T>.() -> Unit): List<T> {
    val builder = ListBuilder<T>()
    builder.init()
    return builder.inner
}

fun <T> listOf(action: () -> T) = listOf(action())

@Deprecated("Use built-in kotlin builders instead")
fun <T> buildSet(init: SetBuilder<T>.() -> Unit): Set<T> {
    val builder = SetBuilder<T>()
    builder.init()
    return builder.inner
}

fun <T> setOf(action: () -> T) = setOf(action())
