package org.vorpal.research.kthelper.graph

import info.leadinglight.jdot.Edge
import info.leadinglight.jdot.Graph
import info.leadinglight.jdot.Node
import info.leadinglight.jdot.enums.Color
import info.leadinglight.jdot.enums.Shape
import info.leadinglight.jdot.impl.Util
import java.io.File
import java.nio.file.Files
import java.nio.file.Path

interface Viewable {
    val graphView: List<GraphView>

    @Suppress("unused")
    fun view(name: String, dot: String, viewer: String): String =
        Util.sh(arrayOf(viewer).plus("file://${toFile(name, dot)}"))

    fun Viewable.toFile(name: String, dot: String): Path =
        toFile(name, dot) {
            setBgColor(Color.X11.transparent)
            setFontSize(12.0)
            setFontName("Fira Mono")
        }

    fun Viewable.toFile(name: String, dot: String, graphConfigurator: Graph.() -> Unit): Path {
        Graph.setDefaultCmd(dot)

        val graph = Graph(name)

        graph.addNodes(*graphView.map { vertex ->
            val node = Node(vertex.name).setShape(Shape.box).setLabel(vertex.label).setFontSize(12.0)
            vertex.nodeConfigurator(node)
            node
        }.toTypedArray())

        graph.graphConfigurator()

        for (node in graphView) {
            for ((successor, label, configurator) in node.successors) {
                graph.addEdge(Edge(node.name, successor.name).also {
                    it.setLabel(label)
                    configurator(it)
                })
            }
        }
        val file = graph.dot2file("svg")
        val newFile = "${file.removeSuffix("out")}svg"
        val resultingFile = File(newFile).toPath()
        Files.move(File(file).toPath(), resultingFile)
        return resultingFile
    }
}

data class GraphView(
    val name: String,
    val label: String,
    val nodeConfigurator: (Node) -> Unit = {}
) {
    val successors: List<Triple<GraphView, String, (Edge) -> Unit>> get() = mutableSuccessors
    private val mutableSuccessors = mutableListOf<Triple<GraphView, String, (Edge) -> Unit>>()

    fun addSuccessor(successor: GraphView, label: String = "", edgeConfigurator: (Edge) -> Unit = {}) {
        mutableSuccessors += Triple(successor, label, edgeConfigurator)
    }
}
