package org.jetbrains.research.kthelper.algorithm

interface TreeNode {
    val parent: TreeNode?
    val children: Set<TreeNode>
}