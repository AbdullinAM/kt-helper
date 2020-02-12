package com.abdullin.kthelper.argorithm

interface TreeNode {
    val parent: TreeNode?
    val children: Set<TreeNode>
}