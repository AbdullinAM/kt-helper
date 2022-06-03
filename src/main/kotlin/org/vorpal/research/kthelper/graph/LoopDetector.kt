package org.vorpal.research.kthelper.graph

import org.vorpal.research.kthelper.collection.stackOf

class LoopDetector<T : PredecessorGraph.PredecessorVertex<T>>(private val graph: PredecessorGraph<T>) {
    fun search(): Map<T, List<T>> {
        val tree = DominatorTreeBuilder(graph).build()
        val backEdges = arrayListOf<Pair<T, T>>()

        for ((current, _) in tree) {
            for (successor in current.successors) {
                val successorTreeNode = tree.getValue(successor)
                if (successorTreeNode.dominates(current)) {
                    backEdges.add(successor to current)
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
