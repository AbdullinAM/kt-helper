package com.abdullin.kthelper.algorithm

import com.abdullin.kthelper.KtException
import com.abdullin.kthelper.collection.queueOf
import com.abdullin.kthelper.collection.stackOf

class NoTopologicalSortingException(msg: String) : KtException(msg)

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

class GraphTraversal<T : Graph.Vertex<T>>(private val graph: Graph<T>) {
    private enum class Colour { WHITE, GREY, BLACK }

    fun <R> dfs(start: T, action: (T) -> R): List<R> {
        val search = mutableListOf<R>()
        val colours = mutableMapOf<T, Colour>()
        val stack = stackOf<T>()
        stack.push(start)
        while (stack.isNotEmpty()) {
            val top = stack.pop()
            if (colours.getOrPut(top) { Colour.WHITE } == Colour.WHITE) {
                colours[top] = Colour.BLACK
                search.add(action(top))
                top.successors.filter { colours[it] != Colour.BLACK }.forEach { stack.push(it) }
            }
        }
        return search
    }

    fun dfs(start: T = graph.entry): List<T> = dfs(start) { it }

    fun <R> bfs(start: T, action: (T) -> R): List<R> {
        val search = mutableListOf<R>()
        val colours = mutableMapOf<T, Colour>()
        val queue = queueOf<T>()
        queue.add(start)
        while (queue.isNotEmpty()) {
            val top = queue.poll()
            if (colours.getOrPut(top) { Colour.WHITE } == Colour.WHITE) {
                colours[top] = Colour.BLACK
                search.add(action(top))
                top.successors.filter { colours[it] != Colour.BLACK }.forEach { queue.add(it) }
            }
        }
        return search
    }

    fun bfs(start: T = graph.entry): List<T> = bfs(start) { it }

    fun <R> topologicalSort(start: T, action: (T) -> R): List<R> {
        val order = arrayListOf<R>()
        val colors = hashMapOf<T, Colour>()

        fun dfs(node: T) {
            val stack = stackOf<Pair<T, Boolean>>()
            stack.push(node to false)
            while (stack.isNotEmpty()) {
                val (top, isPostprocessing) = stack.pop()
                if (colors[top] == Colour.BLACK)
                    continue

                if (isPostprocessing) {
                    colors[top] = Colour.BLACK
                    order += action(top)
                } else {
                    stack.push(top to true)
                    colors[top] = Colour.GREY
                    for (edge in top.successors) {
                        val currentColour = colors.getOrPut(edge) { Colour.WHITE }
                        if (currentColour == Colour.GREY)
                            throw NoTopologicalSortingException("Could not perform topological sort")
                        else if (currentColour != Colour.BLACK)
                            stack.push(edge to false)
                    }
                }
            }
        }

        dfs(start)
        return order.reversed()
    }

    fun topologicalSort(start: T = graph.entry): List<T> = topologicalSort(start) { it }
}

///////////////////////////////////////////////////////////////////////////////

fun <T : Graph.Vertex<T>> Set<T>.asGraph(): Graph<T> = object : Graph<T> {
    override val entry: T
        get() = this@asGraph.first()
    override val nodes = this@asGraph
}

class LoopDetector<T : Graph.Vertex<T>>(private val graph: Graph<T>) {
    fun search(): Map<T, List<T>> {
        val tree = DominatorTreeBuilder(graph).build()
        val backEdges = arrayListOf<Pair<T, T>>()

        for ((current, _) in tree) {
            for (succ in current.successors) {
                val succTreeNode = tree.getValue(succ)
                if (succTreeNode.dominates(current)) {
                    backEdges.add(succ to current)
                }
            }
        }

        val result = hashMapOf<T, MutableList<T>>()
        for ((header, end) in backEdges) {
            val body = arrayListOf(header)
            val stack = stackOf<T>()
            stack.push(end)
            while (stack.isNotEmpty()) {
                val top = stack.pop()
                if (top !in body) {
                    body.add(top)
                    top.predecessors.forEach { stack.push(it) }
                }
            }
            result.getOrPut(header, ::arrayListOf).addAll(body)
        }
        return result
    }
}
