package com.abdullin.kthelper.algorithm

import com.abdullin.kthelper.assertEqualsAny
import kotlin.test.*

class GraphTest : AbstractGraphTest() {
    @Test
    fun testBFS() {
        var bfsOrder = GraphTraversal(simpleGraph).bfs()
        assertEquals(simpleGraph.nodes.toSet(), bfsOrder.toSet())
        assertTrue(bfsOrder.containsAll(simpleGraph.nodes))
        assertEqualsAny(
            bfsOrder.map { it.name },
            listOf("A1", "A2", "B1", "A3", "B2", "B3"),
            listOf("A1", "B1", "B2", "A3", "B2", "B3")
        )

        bfsOrder = GraphTraversal(splittedGraph).bfs()
        assertNotEquals(simpleGraph.nodes.toSet(), bfsOrder.toSet())
        assertEqualsAny(
            bfsOrder.map { it.name },
            listOf("A1", "A2", "B1", "B2"),
            listOf("A1", "B1", "A2", "B2")
        )

        bfsOrder = GraphTraversal(splittedGraph).bfs(splittedGraph["A3"])
        assertNotEquals(simpleGraph.nodes.toSet(), bfsOrder.toSet())
        assertEquals(bfsOrder.map { it.name }, listOf("A3", "B3"))


        bfsOrder = GraphTraversal(sevenBridges).bfs()
        assertEquals(sevenBridges.nodes.toSet(), bfsOrder.toSet())
        assertTrue(bfsOrder.containsAll(sevenBridges.nodes))
        assertEqualsAny(
            bfsOrder.map { it.name },
            listOf("A1", "B1", "A2", "D1", "B2", "C", "D2"),
            listOf("A1", "B1", "A2", "B2", "D1", "C", "D2"),
            listOf("A1", "A2", "B1", "B2", "C", "D1", "D2"),
            listOf("A1", "A2", "B1", "D1", "B2", "C", "D2")
        )

        bfsOrder = GraphTraversal(loopGraph).bfs()
        assertEquals(loopGraph.nodes.toSet(), bfsOrder.toSet())
        assertTrue(bfsOrder.containsAll(loopGraph.nodes))
        assertEquals(bfsOrder.map { it.name }, listOf("A1", "A2", "A3"))
    }

    @Test
    fun testDFS() {
        var dfsOrder = GraphTraversal(simpleGraph).dfs()
        assertEquals(simpleGraph.nodes.toSet(), dfsOrder.toSet())
        assertTrue(dfsOrder.containsAll(simpleGraph.nodes))
        assertEqualsAny(
            dfsOrder.map { it.name },
            listOf("A1", "A2", "A3", "B3", "B1", "B2"),
            listOf("A1", "B1", "B2", "B3", "A2", "A3")
        )

        dfsOrder = GraphTraversal(splittedGraph).dfs()
        assertNotEquals(simpleGraph.nodes.toSet(), dfsOrder.toSet())
        assertEqualsAny(
            dfsOrder.map { it.name },
            listOf("A1", "A2", "B2", "B1"),
            listOf("A1", "B1", "B2", "A2")
        )

        dfsOrder = GraphTraversal(splittedGraph).dfs(splittedGraph["A3"])
        assertNotEquals(simpleGraph.nodes.toSet(), dfsOrder.toSet())
        assertEquals(dfsOrder.map { it.name }, listOf("A3", "B3"))


        dfsOrder = GraphTraversal(sevenBridges).dfs()
        assertEquals(sevenBridges.nodes.toSet(), dfsOrder.toSet())
        assertTrue(dfsOrder.containsAll(sevenBridges.nodes))
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
        assertEquals(loopGraph.nodes.toSet(), dfsOrder.toSet())
        assertTrue(dfsOrder.containsAll(loopGraph.nodes))
        assertEquals(dfsOrder.map { it.name }, listOf("A1", "A2", "A3"))
    }

    @Test
    fun testTopologicalSort() {
        var topologicalSort = GraphTraversal(simpleGraph).topologicalSort()
        assertEquals(simpleGraph.nodes.toSet(), topologicalSort.toSet())
        assertTrue(topologicalSort.containsAll(simpleGraph.nodes))
        assertEqualsAny(
            topologicalSort.map { it.name },
            listOf("A1", "A2", "A3", "B1", "B2", "B3"),
            listOf("A1", "B1", "B2", "A2", "A2", "B3")
        )

        topologicalSort = GraphTraversal(splittedGraph).topologicalSort()
        assertNotEquals(simpleGraph.nodes.toSet(), topologicalSort.toSet())
        assertEqualsAny(
            topologicalSort.map { it.name },
            listOf("A1", "A2", "B1", "B2"),
            listOf("A1", "B1", "A2", "B2")
        )

        topologicalSort = GraphTraversal(splittedGraph).topologicalSort(splittedGraph["A3"])
        assertNotEquals(simpleGraph.nodes.toSet(), topologicalSort.toSet())
        assertEquals(topologicalSort.map { it.name }, listOf("A3", "B3"))


        topologicalSort = GraphTraversal(sevenBridges).topologicalSort()
        assertEquals(sevenBridges.nodes.toSet(), topologicalSort.toSet())
        assertTrue(topologicalSort.containsAll(sevenBridges.nodes))
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