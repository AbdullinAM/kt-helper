package com.kivi.kthelper.algorithm

interface Graph<T : Graph.Vertex<T>> {
    val entry: T
    val nodes: Set<T>

    interface Vertex<out T : Vertex<T>> {
        val predecessors: Set<T>
        val successors: Set<T>
    }

    fun findEntries(): Set<T> {
        val hasEntry = nodes.map { it to false }.toMap().toMutableMap()
        for (node in nodes) {
            node.successors.forEach { hasEntry[it] = true }
        }
        return hasEntry.filter { !it.value }.keys
    }
}

fun <T : Graph.Vertex<T>> Set<T>.asGraph(): Graph<T> = object : Graph<T> {
    override val entry: T
        get() = this@asGraph.first()
    override val nodes = this@asGraph
}
