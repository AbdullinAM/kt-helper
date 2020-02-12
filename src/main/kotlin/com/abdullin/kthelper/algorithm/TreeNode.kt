package com.abdullin.kthelper.algorithm

interface TreeNode {
    val parent: TreeNode?
    val children: Set<TreeNode>
}