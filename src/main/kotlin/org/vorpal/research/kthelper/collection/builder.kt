package org.vorpal.research.kthelper.collection

interface MutableBuilder<T> : MutableCollection<T> {
    val inner: MutableCollection<T>

    operator fun T.unaryPlus() {
        inner += this
    }

    operator fun Collection<T>.unaryPlus() {
        inner += this
    }
}

open class ListBuilder<T>(override val inner: MutableList<T> = mutableListOf<T>()) : MutableBuilder<T>, MutableList<T> by inner
open class SetBuilder<T>(override val inner: MutableSet<T> = mutableSetOf<T>()) : MutableBuilder<T>, MutableSet<T> by inner

fun <T> buildList(init: ListBuilder<T>.() -> Unit): List<T> {
    val builder = ListBuilder<T>()
    builder.init()
    return builder.inner
}

fun <T> listOf(action: () -> T) = listOf(action())

fun <T> buildSet(init: SetBuilder<T>.() -> Unit): Set<T> {
    val builder = SetBuilder<T>()
    builder.init()
    return builder.inner
}

fun <T> setOf(action: () -> T) = setOf(action())