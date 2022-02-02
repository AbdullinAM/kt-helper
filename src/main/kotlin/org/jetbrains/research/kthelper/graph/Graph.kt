package org.jetbrains.research.kthelper.graph

interface Graph<T : Graph.Vertex<T>> {
    val entry: T
    val nodes: Set<T>

    interface Vertex<out T : Vertex<T>> {
        val successors: Set<T>
    }

    fun findEntries(): Set<T> {
        val hasEntry = nodes.associateWith { false }.toMutableMap()
        for (node in nodes) {
            node.successors.forEach { hasEntry[it] = true }
        }
        return hasEntry.filter { !it.value }.keys
    }
}

interface PredecessorGraph<T : PredecessorGraph.PredecessorVertex<T>> : Graph<T> {
    interface PredecessorVertex<out T : PredecessorVertex<T>> : Graph.Vertex<T> {
        val predecessors: Set<T>
    }
}

fun <T : Graph.Vertex<T>> Set<T>.asGraph(): Graph<T> = object : Graph<T> {
    override val entry: T
        get() = this@asGraph.first()
    override val nodes = this@asGraph
}
