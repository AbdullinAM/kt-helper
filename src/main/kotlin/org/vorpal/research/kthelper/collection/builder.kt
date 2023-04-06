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

fun <T> listOf(action: () -> T): List<T> = listOf(action())
fun <T> listOf(size: Int, action: (Int) -> T): List<T> = (0 until size).map(action)
fun <T> listOf(vararg actions: () -> T): List<T> = actions.map { it() }

@Deprecated("Use built-in kotlin builders instead")
fun <T> buildSet(init: SetBuilder<T>.() -> Unit): Set<T> {
    val builder = SetBuilder<T>()
    builder.init()
    return builder.inner
}

fun <T> setOf(action: () -> T): Set<T> = setOf(action())
fun <T> setOf(size: Int, action: (Int) -> T): Set<T> = (0 until size).mapTo(mutableSetOf(), action)
fun <T> setOf(vararg actions: () -> T): Set<T> = actions.mapTo(mutableSetOf()) { it() }


fun <T> buildMutableList(builder: MutableList<T>.() -> Unit): MutableList<T> {
    val res = mutableListOf<T>()
    res.builder()
    return res
}

fun <T> buildMutableSet(builder: MutableSet<T>.() -> Unit): MutableSet<T> {
    val res = mutableSetOf<T>()
    res.builder()
    return res
}

fun <K, V> buildMutableMap(builder: MutableMap<K, V>.() -> Unit): MutableMap<K, V> {
    val res = mutableMapOf<K, V>()
    res.builder()
    return res
}
