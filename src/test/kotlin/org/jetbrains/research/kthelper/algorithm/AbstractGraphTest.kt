package org.jetbrains.research.kthelper.algorithm

import org.jetbrains.research.kthelper.algorithm.impl.GraphImpl
import kotlin.test.*

abstract class AbstractGraphTest {
    lateinit var simpleGraph: GraphImpl
    lateinit var splittedGraph: GraphImpl
    lateinit var sevenBridges: GraphImpl
    lateinit var loopGraph: GraphImpl

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
            addEdge(a2, b2)
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

}