package com.abdullin.kthelper.argorithm

import com.abdullin.kthelper.KtException
import java.util.*

class NoTopologicalSortingException(msg: String) : KtException(msg)

interface GraphNode<out T : Any> {
    val predecessors: Set<T>
    val successors: Set<T>
}

interface Graph<T : GraphNode<T>> {
    val entry: T
    val nodes: Set<T>

    fun findEntries(): Set<T> {
        val hasEntry = nodes.map { it to false }.toMap().toMutableMap()
        for (node in nodes) {
            node.successors.forEach { hasEntry[it] = true }
        }
        return hasEntry.filter { !it.value }.keys
    }
}

class GraphTraversal<T : GraphNode<T>>(private val graph: Graph<T>) {
    private enum class Colour { WHITE, GREY, BLACK }

    fun <R> dfs(action: (T) -> R): List<R> {
        val node = graph.entry
        val search = mutableListOf<R>()
        val colours = mutableMapOf<T, Colour>()
        val stack = ArrayDeque<T>()
        stack.push(node)
        while (stack.isNotEmpty()) {
            val top = stack.pollLast()!!
            if (colours.getOrPut(top) { Colour.WHITE } == Colour.WHITE) {
                colours[top] = Colour.BLACK
                search.add(action(top))
                top.successors.filter { colours[it] != Colour.BLACK }.forEach { stack.push(it) }
            }
        }
        return search
    }

    fun dfs(): List<T> = dfs { it }

    fun <R> bfs(action: (T) -> R): List<R> {
        val node = graph.entry
        val search = mutableListOf<R>()
        val colours = mutableMapOf<T, Colour>()
        val stack = ArrayDeque<T>()
        stack.push(node)
        while (stack.isNotEmpty()) {
            val top = stack.poll()!!
            if (colours.getOrPut(top) { Colour.WHITE } == Colour.WHITE) {
                colours[top] = Colour.BLACK
                search.add(action(top))
                top.successors.filter { colours[it] != Colour.BLACK }.forEach { stack.push(it) }
            }
        }
        return search
    }

    fun bfs(): List<T> = bfs { it }

    fun <R> topologicalSort(action: (T) -> R): List<R> {
        val order = arrayListOf<R>()
        val colors = hashMapOf<T, Colour>()

        fun dfs(node: T) {
            val stack = ArrayDeque<Pair<T, Boolean>>()
            stack.push(node to false)
            while (stack.isNotEmpty()) {
                val (top, isPostprocessing) = stack.poll()
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

        dfs(graph.entry)
        return order
    }

    fun topologicalSort(): List<T> = topologicalSort { it }
}

///////////////////////////////////////////////////////////////////////////////

fun <T : GraphNode<T>> Set<T>.asGraph(): Graph<T> = object : Graph<T> {
    override val entry: T
        get() = this@asGraph.first()
    override val nodes = this@asGraph
}

class LoopDetector<T : GraphNode<T>>(private val graph: Graph<T>) {
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
            val stack = ArrayDeque<T>()
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
