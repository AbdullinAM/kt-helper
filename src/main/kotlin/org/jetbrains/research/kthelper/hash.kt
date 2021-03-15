package org.jetbrains.research.kthelper

fun defaultHashCode(vararg objects: Any) = objects.fold(1) { acc, any -> 31 * acc + any.hashCode() }