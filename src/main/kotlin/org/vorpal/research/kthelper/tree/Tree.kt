package org.vorpal.research.kthelper.tree


@Suppress("unused")
interface Tree<T : Tree.TreeNode<T>> {
    val root: T?
    val nodes: Set<T>

    interface TreeNode<T> {
        val parent: T?
        val children: Set<T>
    }
}
