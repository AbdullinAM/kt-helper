@file:Suppress("NOTHING_TO_INLINE")

package org.vorpal.research.kthelper

@Deprecated("use auto generated hash code")
inline fun defaultHashCode(vararg objects: Any): Int {
    var result = 1
    for (element in objects)
        result = 31 * result + element.hashCode()
    return result
}
