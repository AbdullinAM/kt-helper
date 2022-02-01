package org.jetbrains.research.kthelper.algorithm

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

    fun view(name: String, dot: String, viewer: String): String =
        Util.sh(arrayOf(viewer).plus("file://${toFile(name, dot)}"))

    fun Viewable.toFile(name: String, dot: String): Path {
        Graph.setDefaultCmd(dot)

        val graph = Graph(name)

        graph.addNodes(*graphView.map {
            Node(it.name).setShape(Shape.box).setLabel(it.label).setFontSize(12.0)
        }.toTypedArray())

        graph.setBgColor(Color.X11.transparent)

        for (node in graphView) {
            for ((successor, label) in node.successors) {
                graph.addEdge(Edge(node.name, successor.name).also {
                    it.setLabel(label)
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
    val label: String
) {
    val successors: List<Pair<GraphView, String>> get() = mutableSuccessors
    private val mutableSuccessors = mutableListOf<Pair<GraphView, String>>()

    fun addSuccessor(successor: GraphView, label: String = "") {
        mutableSuccessors += successor to label
    }
}
