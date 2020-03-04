package com.abdullin.kthelper.collection

abstract class MutableBuilder<T> {
    abstract val inner: MutableCollection<T>

    operator fun T.unaryPlus() {
        inner += this
    }

    operator fun Collection<T>.unaryPlus() {
        inner += this
    }
}

class ListBuilder<T> : MutableBuilder<T>() {
    override val inner = mutableListOf<T>()
}

fun <T> buildList(init: MutableBuilder<T>.() -> Unit): List<T> {
    val builder = ListBuilder<T>()
    builder.init()
    return builder.inner
}

fun <T> listOf(action: () -> T) = listOf(action())


class SetBuilder<T> : MutableBuilder<T>() {
    override val inner = mutableSetOf<T>()
}

fun <T> buildSet(init: MutableBuilder<T>.() -> Unit): Set<T> {
    val builder = SetBuilder<T>()
    builder.init()
    return builder.inner
}

fun <T> setOf(action: () -> T) = setOf(action())