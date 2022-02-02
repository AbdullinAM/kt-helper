package org.jetbrains.research.kthelper.tree.impl

import org.jetbrains.research.kthelper.graph.PredecessorGraph

data class VertexImpl(val name: String) :
    PredecessorGraph.PredecessorVertex<VertexImpl> {
    internal val mutablePredecessors = linkedSetOf<VertexImpl>()
    internal val mutableSuccessors = linkedSetOf<VertexImpl>()


    override val predecessors: Set<VertexImpl>
        get() = mutablePredecessors
    override val successors: Set<VertexImpl>
        get() = mutableSuccessors
}

class GraphImpl(vararg elements: String) :
    PredecessorGraph<VertexImpl> {
    private val mutableNodes = linkedSetOf<VertexImpl>()
    private val map = mutableMapOf<String, VertexImpl>()

    init {
        for (element in elements) {
            val vertex = VertexImpl(element)
            map[element] = vertex
            mutableNodes.add(vertex)
        }
    }

    override val entry: VertexImpl
        get() = mutableNodes.first()
    override val nodes: Set<VertexImpl>
        get() = mutableNodes

    operator fun get(name: String) = map.getValue(name)

    fun addVertex(name: String): VertexImpl {
        require(name !in map)
        val vertex = VertexImpl(name)
        map[name] = vertex
        mutableNodes.add(vertex)
        return vertex
    }

    fun addEdge(from: String, to: String) {
        val fromVertex = map.getValue(from)
        val toVertex = map.getValue(to)

        addEdge(fromVertex, toVertex)
    }

    fun addEdge(from: VertexImpl, to: VertexImpl) {
        from.mutableSuccessors.add(to)
        to.mutablePredecessors.add(from)
    }
}
