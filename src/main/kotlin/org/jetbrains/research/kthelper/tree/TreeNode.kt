package org.jetbrains.research.kthelper.tree

interface TreeNode {
    val parent: TreeNode?
    val children: Set<TreeNode>
}

interface Tree {
    val root: TreeNode?
    val nodes: Set<TreeNode>
}