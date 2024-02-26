package org.vorpal.research.kthelper.graph

import org.vorpal.research.kthelper.KtException
import org.vorpal.research.kthelper.collection.queueOf
import org.vorpal.research.kthelper.collection.stackOf
import java.util.*

class NoTopologicalSortingException(msg: String) : KtException(msg)

@Suppress("MemberVisibilityCanBePrivate")
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

        dfs(start)
        return order.reversed()
    }

    fun topologicalSort(start: T = graph.entry): List<T> = topologicalSort(start) { it }

    fun components(): Pair<UInt, Map<T, UInt>> {
        var componentNumber = 0U
        val components = mutableMapOf<T, UInt>()
        for (node in graph.nodes) {
            if (node in components) continue
            dfs(node) { components[it] = componentNumber }
            ++componentNumber
        }
        return (componentNumber + 1U) to components
    }
}
