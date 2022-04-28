package org.vorpal.research.kthelper.collection

class MapWithDefault<K, V>(
    private val inner: Map<K, V>,
    val default: V
) : Map<K, V> by inner {
    override fun get(key: K): V = inner[key] ?: default
}

class MutableMapWithDefault<K, V>(
    private val inner: MutableMap<K, V>,
    val default: V
) : MutableMap<K, V> by inner {
    override fun get(key: K): V = inner[key] ?: default
}


fun <K, V> Map<K, V>.withDefault(default: V) = MapWithDefault(this, default)
fun <K, V> Map<K, V>.withDefault(default: () -> V) = MapWithDefault(this, default())

fun <K, V> MutableMap<K, V>.withDefault(default: V) = MutableMapWithDefault(this, default)
fun <K, V> MutableMap<K, V>.withDefault(default: () -> V) = MutableMapWithDefault(this, default())