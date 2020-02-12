package com.abdullin.kthelper.algorithm

import com.abdullin.kthelper.assertEqualsAny
import kotlin.test.*

class GraphTest {
    lateinit var simpleGraph: GraphImpl
    lateinit var splittedGraph: GraphImpl
    lateinit var sevenBridges: GraphImpl
    lateinit var loopGraph: GraphImpl

    data class VertexImpl(val name: String) : Graph.Vertex<VertexImpl> {
        val mutablePredecessors = linkedSetOf<VertexImpl>()
        val mutableSuccessors = linkedSetOf<VertexImpl>()


        override val predecessors: Set<VertexImpl>
            get() = mutablePredecessors
        override val successors: Set<VertexImpl>
            get() = mutableSuccessors
    }

    class GraphImpl(vararg elements: String) : Graph<VertexImpl> {
        val mutableNodes = linkedSetOf<VertexImpl>()
        val map = mutableMapOf<String, VertexImpl>()

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

    @BeforeTest
    fun initGraph() {
        //    A1 -> A2 -> A3
        //    V           V
        //    B1 -> B2 -> B3
        simpleGraph = GraphImpl().apply {
            val a1 = addVertex("A1")
            val a2 = addVertex("A2")
            val a3 = addVertex("A3")
            val b1 = addVertex("B1")
            val b2 = addVertex("B2")
            val b3 = addVertex("B3")
            addEdge(a1, a2)
            addEdge(a2, a3)
            addEdge(a3, b3)
            addEdge(a1, b1)
            addEdge(b1, b2)
            addEdge(b2, b3)
        }
        //    A1 -> A2    A3
        //    V     V     V
        //    B1 -> B2    B3
        splittedGraph = GraphImpl().apply {
            val a1 = addVertex("A1")
            val a2 = addVertex("A2")
            val a3 = addVertex("A3")
            val b1 = addVertex("B1")
            val b2 = addVertex("B2")
            val b3 = addVertex("B3")
            addEdge(a1, a2)
            addEdge(a1, b1)
            addEdge(b1, b2)

            addEdge(a3, b3)
        }
        //    A1 -> A2 ---
        //    |     |     |
        //    V     V     V
        //    B1 -> B2 -> C
        //    |     |     ^
        //    V     V     |
        //    D1 -> D2 ---
        sevenBridges = GraphImpl().apply {
            val a1 = addVertex("A1")
            val a2 = addVertex("A2")
            val b1 = addVertex("B1")
            val b2 = addVertex("B2")
            val c = addVertex("C")
            val d1 = addVertex("D1")
            val d2 = addVertex("D2")
            addEdge(a1, a2)
            addEdge(b1, b2)
            addEdge(d1, d2)
            addEdge(a1, b1)
            addEdge(b1, d1)
            addEdge(a2, b2)
            addEdge(b2, d2)
            addEdge(a2, c)
            addEdge(b2, c)
            addEdge(d2, c)
        }
        //    A1 ---
        //    ^     V
        //    A3 <- A2
        loopGraph = GraphImpl().apply {
            val a1 = addVertex("A1")
            val a2 = addVertex("A2")
            val a3 = addVertex("A3")
            addEdge(a1, a2)
            addEdge(a2, a3)
            addEdge(a3, a1)
        }
    }

    @Test
    fun testBFS() {
        var bfsOrder = GraphTraversal(simpleGraph).bfs()
        assertEquals(simpleGraph.mutableNodes.toSet(), bfsOrder.toSet())
        assertTrue(bfsOrder.containsAll(simpleGraph.mutableNodes))
        assertEqualsAny(
            bfsOrder.map { it.name },
            listOf("A1", "A2", "B1", "A3", "B2", "B3"),
            listOf("A1", "B1", "B2", "A3", "B2", "B3")
        )

        bfsOrder = GraphTraversal(splittedGraph).bfs()
        assertNotEquals(simpleGraph.mutableNodes.toSet(), bfsOrder.toSet())
        assertEqualsAny(
            bfsOrder.map { it.name },
            listOf("A1", "A2", "B1", "B2"),
            listOf("A1", "B1", "A2", "B2")
        )

        bfsOrder = GraphTraversal(splittedGraph).bfs(splittedGraph["A3"])
        assertNotEquals(simpleGraph.mutableNodes.toSet(), bfsOrder.toSet())
        assertEquals(bfsOrder.map { it.name }, listOf("A3", "B3"))


        bfsOrder = GraphTraversal(sevenBridges).bfs()
        assertEquals(sevenBridges.mutableNodes.toSet(), bfsOrder.toSet())
        assertTrue(bfsOrder.containsAll(sevenBridges.mutableNodes))
        assertEqualsAny(
            bfsOrder.map { it.name },
            listOf("A1", "B1", "A2", "D1", "B2", "C", "D2"),
            listOf("A1", "B1", "A2", "B2", "D1", "C", "D2"),
            listOf("A1", "A2", "B1", "B2", "C", "D1", "D2"),
            listOf("A1", "A2", "B1", "D1", "B2", "C", "D2")
        )

        bfsOrder = GraphTraversal(loopGraph).bfs()
        assertEquals(loopGraph.mutableNodes.toSet(), bfsOrder.toSet())
        assertTrue(bfsOrder.containsAll(loopGraph.mutableNodes))
        assertEquals(bfsOrder.map { it.name }, listOf("A1", "A2", "A3"))
    }

    @Test
    fun testDFS() {
        var dfsOrder = GraphTraversal(simpleGraph).dfs()
        assertEquals(simpleGraph.mutableNodes.toSet(), dfsOrder.toSet())
        assertTrue(dfsOrder.containsAll(simpleGraph.mutableNodes))
        assertEqualsAny(
            dfsOrder.map { it.name },
            listOf("A1", "A2", "A3", "B3", "B1", "B2"),
            listOf("A1", "B1", "B2", "B3", "A2", "A3")
        )

        dfsOrder = GraphTraversal(splittedGraph).dfs()
        assertNotEquals(simpleGraph.mutableNodes.toSet(), dfsOrder.toSet())
        assertEqualsAny(
            dfsOrder.map { it.name },
            listOf("A1", "A2", "B2", "B1"),
            listOf("A1", "B1", "B2", "A2")
        )

        dfsOrder = GraphTraversal(splittedGraph).dfs(splittedGraph["A3"])
        assertNotEquals(simpleGraph.mutableNodes.toSet(), dfsOrder.toSet())
        assertEquals(dfsOrder.map { it.name }, listOf("A3", "B3"))


        dfsOrder = GraphTraversal(sevenBridges).dfs()
        assertEquals(sevenBridges.mutableNodes.toSet(), dfsOrder.toSet())
        assertTrue(dfsOrder.containsAll(sevenBridges.mutableNodes))
        assertEqualsAny(
            dfsOrder.map { it.name },
            listOf("A1", "B1", "D1", "D2", "C", "B2", "A2"),
            listOf("A1", "B1", "B2", "D2", "C", "D1", "A2"),
            listOf("A1", "B1", "B2", "C", "D2", "D1", "A2"),
            listOf("A1", "A2", "C", "B2", "B1", "D1", "D2"),
            listOf("A1", "A2", "B2", "C", "D2", "B1", "D1"),
            listOf("A1", "A2", "B2", "D2", "C", "B1", "D1")
        )

        dfsOrder = GraphTraversal(loopGraph).dfs()
        assertEquals(loopGraph.mutableNodes.toSet(), dfsOrder.toSet())
        assertTrue(dfsOrder.containsAll(loopGraph.mutableNodes))
        assertEquals(dfsOrder.map { it.name }, listOf("A1", "A2", "A3"))
    }

    @Test
    fun testTopologicalSort() {
        var topologicalSort = GraphTraversal(simpleGraph).topologicalSort()
        assertEquals(simpleGraph.mutableNodes.toSet(), topologicalSort.toSet())
        assertTrue(topologicalSort.containsAll(simpleGraph.mutableNodes))
        assertEqualsAny(
            topologicalSort.map { it.name },
            listOf("A1", "A2", "A3", "B1", "B2", "B3"),
            listOf("A1", "B1", "B2", "A2", "A2", "B3")
        )

        topologicalSort = GraphTraversal(splittedGraph).topologicalSort()
        assertNotEquals(simpleGraph.mutableNodes.toSet(), topologicalSort.toSet())
        assertEqualsAny(
            topologicalSort.map { it.name },
            listOf("A1", "A2", "B1", "B2"),
            listOf("A1", "B1", "A2", "B2")
        )

        topologicalSort = GraphTraversal(splittedGraph).topologicalSort(splittedGraph["A3"])
        assertNotEquals(simpleGraph.mutableNodes.toSet(), topologicalSort.toSet())
        assertEquals(topologicalSort.map { it.name }, listOf("A3", "B3"))


        topologicalSort = GraphTraversal(sevenBridges).topologicalSort()
        assertEquals(sevenBridges.mutableNodes.toSet(), topologicalSort.toSet())
        assertTrue(topologicalSort.containsAll(sevenBridges.mutableNodes))
        assertEqualsAny(
            topologicalSort.map { it.name },
            listOf("A1", "B1", "D1", "A2", "B2", "D2", "C"),
            listOf("A1", "A2", "B1", "B2", "D1", "D2", "C"),
            listOf("A1", "A2", "B1", "D1", "B2", "D2", "C")
        )

        assertFailsWith(NoTopologicalSortingException::class) { GraphTraversal(loopGraph).topologicalSort() }
    }
    @Test
    fun testLoopSearch() {
        assertTrue(LoopDetector(simpleGraph).search().isEmpty())
        assertTrue(LoopDetector(splittedGraph).search().isEmpty())
        assertTrue(LoopDetector(sevenBridges).search().isEmpty())

        val loops = LoopDetector(loopGraph).search().map { it.key.name to it.value.map { it.name } }.toMap()
        assertEquals(1, loops.size)
        assertEquals(setOf("A1"), loops.keys.toSet())
        assertEquals(setOf("A1", "A2", "A3"), loops.getValue("A1").toSet())
    }
}