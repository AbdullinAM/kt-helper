package org.jetbrains.research.kthelper.algorithm

import org.jetbrains.research.kthelper.collection.stackOf
import java.util.*
import kotlin.math.min

class DominatorTreeNode<T : Graph.Vertex<T>>(val value: T) : TreeNode {
    var idom: DominatorTreeNode<T>? = null
        internal set
    internal val dominates = hashSetOf<DominatorTreeNode<T>>()

    override val children: Set<DominatorTreeNode<T>>
        get() = dominates
    override val parent get() = idom

    fun dominates(node: T): Boolean = when {
        dominates.any { it.value == node } -> true
        else -> dominates.any { it.dominates(node) }
    }
}

class DominatorTree<T : Graph.Vertex<T>>
    : MutableMap<T, DominatorTreeNode<T>> by mutableMapOf<T, DominatorTreeNode<T>>(), Viewable {
    override val graphView: List<GraphView>
        get() {
            val nodes = hashMapOf<String, GraphView>()
            this.keys.forEach {
                nodes[it.toString().replace("\"", "\\\"")] =
                    GraphView(
                        it.toString().replace("\"", "\\\""),
                        it.toString().replace("\"", "\\\"")
                    )
            }

            this.entries.forEach { (node, tnode) ->
                val current = nodes.getValue(node.toString().replace("\"", "\\\""))
                val idom = tnode.idom?.value?.toString()
                if (idom != null) {
                    val parent = nodes.getValue(idom.replace("\"", "\\\""))
                    parent.addSuccessor(current)
                }
            }

            return nodes.values.toList()
        }
}

class DominatorTreeBuilder<T : Graph.Vertex<T>>(private val graph: Graph<T>) {
    private val tree = DominatorTree<T>()

    private var nodeCounter: Int = 0
    private val dfsTree = hashMapOf<T, Int>()
    private val reverseMapping = arrayListOf<T?>()
    private val reverseGraph = arrayListOf<ArrayList<Int>>()
    private val parents = arrayListOf<Int>()
    private val labels = arrayListOf<Int>()
    private val sdom = arrayListOf<Int>()
    private val dom = arrayListOf<Int>()
    private val dsu = arrayListOf<Int>()
    private val bucket = arrayListOf<MutableSet<Int>>()

    init {
        for (i in graph.nodes) {
            parents.add(-1)
            labels.add(-1)
            sdom.add(-1)
            dom.add(-1)
            dsu.add(-1)
            reverseMapping.add(null)
            dfsTree[i] = -1
            bucket.add(mutableSetOf())
            reverseGraph.add(arrayListOf())
        }
        tree.putAll(graph.nodes.map { it to DominatorTreeNode(it) })
    }

    private fun union(u: Int, v: Int) {
        dsu[v] = u
    }

    private fun find(u: Int, x: Int = 0): Int {
        if (u < 0) return u
        if (u == dsu[u]) return if (x != 0) -1 else u
        val v = find(dsu[u], x + 1)
        if (v < 0) return u
        if (sdom[labels[dsu[u]]] < sdom[labels[u]]) labels[u] = labels[dsu[u]]
        dsu[u] = v
        return if (x != 0) v else labels[u]
    }

    private fun rdfs(node: T) {
        dfsTree[node] = nodeCounter
        reverseMapping[nodeCounter] = node
        labels[nodeCounter] = nodeCounter
        sdom[nodeCounter] = nodeCounter
        dsu[nodeCounter] = nodeCounter
        nodeCounter++
        for (it in node.successors) {
            if (dfsTree.getValue(it) == -1) {
                rdfs(it)
                parents[dfsTree.getValue(it)] = dfsTree.getValue(node)
            }
            reverseGraph[dfsTree.getValue(it)].add(dfsTree.getValue(node))
        }
    }

    private fun dfs(node: T) {
        val stack = stackOf<Pair<T, Int>>()
        stack.push(node to -1)

        while (stack.isNotEmpty()) {
            val (top, parent) = stack.pop()
            if (dfsTree.getValue(top) != -1) {
                if (parent >= 0) reverseGraph[dfsTree.getValue(top)].add(parent)
                continue
            }

            dfsTree[top] = nodeCounter
            reverseMapping[nodeCounter] = top
            labels[nodeCounter] = nodeCounter
            sdom[nodeCounter] = nodeCounter
            dsu[nodeCounter] = nodeCounter
            nodeCounter++
            if (parent >= 0) {
                parents[dfsTree.getValue(top)] = parent
                reverseGraph[dfsTree.getValue(top)].add(parent)
            }

            for (it in top.successors.reversed()) {
                if (dfsTree.getValue(it) == -1) {
                    stack.push(it to dfsTree.getValue(top))
                } else {
                    reverseGraph[dfsTree.getValue(it)].add(dfsTree.getValue(top))
                }
            }
        }
    }

    fun build(): DominatorTree<T> {
        for (it in graph.nodes) if (dfsTree[it] == -1) dfs(it)
        val n = dfsTree.size
        for (i in n - 1 downTo 0) {
            for (j in reverseGraph[i]) {
                sdom[i] = min(sdom[i], sdom[find(j)])
            }
            if (i > 0) bucket[sdom[i]].add(i)
            for (j in bucket[i]) {
                val v = find(j)
                if (sdom[v] == sdom[j]) dom[j] = sdom[j]
                else dom[j] = v
            }
            if (i > 0) union(parents[i], i)
        }
        for (i in 1 until n) {
            if (dom[i] != sdom[i]) dom[i] = dom[dom[i]]
        }
        for ((it, idom) in dom.withIndex()) {
            val current = reverseMapping[it]!!
            if (idom != -1) {
                val dominator = reverseMapping[idom]!!
                tree.getValue(dominator).dominates.add(tree.getValue(current))
                tree.getValue(current).idom = tree.getValue(dominator)
            }
        }
        for (it in tree) {
            if (it.key == it.value.idom?.value) it.value.idom = null
        }
        return tree
    }
}
