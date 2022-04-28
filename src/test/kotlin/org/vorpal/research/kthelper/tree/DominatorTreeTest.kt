package org.vorpal.research.kthelper.tree

import org.vorpal.research.kthelper.graph.DominatorTreeBuilder
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class DominatorTreeTest : AbstractGraphTest() {

    @Test
    fun testDominatorTree() {
        //    A1 -> A2 -> A3
        //    V           V
        //    B1 -> B2 -> B3
        var dominatorTree = DominatorTreeBuilder(simpleGraph).build()
        for (vertex in simpleGraph.nodes.filter { it.name !in listOf("A3", "B2") }) {
            val domTreeNode = dominatorTree[vertex]!!
            assertTrue(vertex.successors.all { domTreeNode.dominates(it) })
        }
        assertEquals(simpleGraph["A1"], dominatorTree[simpleGraph["B3"]]?.idom?.value)

        //    A1 -> A2    A3
        //    V     V     V
        //    B1 -> B2    B3
        dominatorTree = DominatorTreeBuilder(splittedGraph).build()
        assertTrue(splittedGraph["A1"].successors.all { dominatorTree[it]?.parent == dominatorTree[splittedGraph["A1"]] })
        assertEquals(splittedGraph["A1"], dominatorTree[splittedGraph["B2"]]?.idom?.value)
        assertEquals(splittedGraph["A3"], dominatorTree[splittedGraph["B3"]]?.idom?.value)

        //    A1 -> A2 ---
        //    |     |     |
        //    V     V     V
        //    B1 -> B2 -> C
        //    |     |     ^
        //    V     V     |
        //    D1 -> D2 ---
        dominatorTree = DominatorTreeBuilder(sevenBridges).build()
        assertEquals(sevenBridges["A1"], dominatorTree[sevenBridges["B2"]]?.idom?.value)
        assertEquals(sevenBridges["A1"], dominatorTree[sevenBridges["D2"]]?.idom?.value)
        assertEquals(sevenBridges["A1"], dominatorTree[sevenBridges["C"]]?.idom?.value)

        //    A1 ---
        //    ^     V
        //    A3 <- A2
        dominatorTree = DominatorTreeBuilder(loopGraph).build()
        assertEquals(loopGraph["A1"], dominatorTree[loopGraph["A2"]]?.idom?.value)
        assertEquals(loopGraph["A2"], dominatorTree[loopGraph["A3"]]?.idom?.value)
        assertNull(dominatorTree[loopGraph["A1"]]?.idom?.value)
    }
}