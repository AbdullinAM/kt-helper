@file:Suppress("unused")

package org.vorpal.research.kthelper.collection

import java.util.*

fun <T> queueOf(vararg elements: T): Queue<T> = ArrayDeque(elements.toList())
fun <T> queueOf(elements: Collection<T>): Queue<T> = ArrayDeque(elements.toList())

fun <T> dequeOf(vararg elements: T): Deque<T> = ArrayDeque(elements.toList())
fun <T> dequeOf(elements: Collection<T>): Deque<T> = ArrayDeque(elements)

fun <T> Collection<T>.firstOrDefault(default: T): T = firstOrNull() ?: default
fun <T> Collection<T>.firstOrDefault(predicate: (T) -> Boolean, default: T): T = firstOrNull(predicate) ?: default

fun <T> Collection<T>.firstOrElse(action: () -> T): T = firstOrNull() ?: action()
fun <T> Collection<T>.firstOrElse(predicate: (T) -> Boolean, action: () -> T): T = firstOrNull(predicate) ?: action()

fun <T> Collection<T>.lastOrDefault(default: T): T = lastOrNull() ?: default
fun <T> Collection<T>.lastOrDefault(predicate: (T) -> Boolean, default: T): T = lastOrNull(predicate) ?: default

fun <T> Collection<T>.lastOrElse(action: () -> T): T = lastOrNull() ?: action()
fun <T> Collection<T>.lastOrElse(predicate: (T) -> Boolean, action: () -> T): T = lastOrNull(predicate) ?: action()

inline fun <A, reified B> Collection<A>.mapToArray(body: (A) -> B): Array<B> {
    val arr = arrayOfNulls<B>(size)
    var i = 0
    for(e in this) arr[i++] = body(e)
    @Suppress("UNCHECKED_CAST")
    return arr as Array<B>
}

inline fun <A> Collection<A>.mapToBooleanArray(body: (A) -> Boolean): BooleanArray {
    val arr = BooleanArray(size)
    var i = 0
    for(e in this) arr[i++] = body(e)
    @Suppress("UNCHECKED_CAST")
    return arr

}

inline fun <A> Collection<A>.mapToCharArray(body: (A) -> Char): CharArray {
    val arr = CharArray(size)
    var i = 0
    for(e in this) arr[i++] = body(e)
    @Suppress("UNCHECKED_CAST")
    return arr

}

inline fun <A> Collection<A>.mapToByteArray(body: (A) -> Byte): ByteArray {
    val arr = ByteArray(size)
    var i = 0
    for(e in this) arr[i++] = body(e)
    @Suppress("UNCHECKED_CAST")
    return arr

}

inline fun <A> Collection<A>.mapToShortArray(body: (A) -> Short): ShortArray {
    val arr = ShortArray(size)
    var i = 0
    for(e in this) arr[i++] = body(e)
    @Suppress("UNCHECKED_CAST")
    return arr

}

inline fun <A> Collection<A>.mapToIntArray(body: (A) -> Int): IntArray {
    val arr = IntArray(size)
    var i = 0
    for(e in this) arr[i++] = body(e)
    @Suppress("UNCHECKED_CAST")
    return arr

}

inline fun <A> Collection<A>.mapToLongArray(body: (A) -> Long): LongArray {
    val arr = LongArray(size)
    var i = 0
    for(e in this) arr[i++] = body(e)
    @Suppress("UNCHECKED_CAST")
    return arr

}

inline fun <A> Collection<A>.mapToFloatArray(body: (A) -> Float): FloatArray {
    val arr = FloatArray(size)
    var i = 0
    for(e in this) arr[i++] = body(e)
    @Suppress("UNCHECKED_CAST")
    return arr

}

inline fun <A> Collection<A>.mapToDoubleArray(body: (A) -> Double): DoubleArray {
    val arr = DoubleArray(size)
    var i = 0
    for(e in this) arr[i++] = body(e)
    @Suppress("UNCHECKED_CAST")
    return arr

}

inline fun <A, reified B> Array<A>.mapToArray(body: (A) -> B): Array<B> {
    val arr = arrayOfNulls<B>(size)
    var i = 0
    for(e in this) arr[i++] = body(e)
    @Suppress("UNCHECKED_CAST")
    return arr as Array<B>
}

inline fun <reified B> BooleanArray.mapToArray(body: (Boolean) -> B): Array<B> {
    val arr = arrayOfNulls<B>(size)
    var i = 0
    for(e in this) arr[i++] = body(e)
    @Suppress("UNCHECKED_CAST")
    return arr as Array<B>
}

inline fun <reified B> CharArray.mapToArray(body: (Char) -> B): Array<B> {
    val arr = arrayOfNulls<B>(size)
    var i = 0
    for(e in this) arr[i++] = body(e)
    @Suppress("UNCHECKED_CAST")
    return arr as Array<B>
}

inline fun <reified B> ShortArray.mapToArray(body: (Short) -> B): Array<B> {
    val arr = arrayOfNulls<B>(size)
    var i = 0
    for(e in this) arr[i++] = body(e)
    @Suppress("UNCHECKED_CAST")
    return arr as Array<B>
}

inline fun <reified B> ByteArray.mapToArray(body: (Byte) -> B): Array<B> {
    val arr = arrayOfNulls<B>(size)
    var i = 0
    for(e in this) arr[i++] = body(e)
    @Suppress("UNCHECKED_CAST")
    return arr as Array<B>
}

inline fun <reified B> IntArray.mapToArray(body: (Int) -> B): Array<B> {
    val arr = arrayOfNulls<B>(size)
    var i = 0
    for(e in this) arr[i++] = body(e)
    @Suppress("UNCHECKED_CAST")
    return arr as Array<B>
}

inline fun <reified B> LongArray.mapToArray(body: (Long) -> B): Array<B> {
    val arr = arrayOfNulls<B>(size)
    var i = 0
    for(e in this) arr[i++] = body(e)
    @Suppress("UNCHECKED_CAST")
    return arr as Array<B>
}

inline fun <reified B> FloatArray.mapToArray(body: (Float) -> B): Array<B> {
    val arr = arrayOfNulls<B>(size)
    var i = 0
    for(e in this) arr[i++] = body(e)
    @Suppress("UNCHECKED_CAST")
    return arr as Array<B>
}

inline fun <reified B> DoubleArray.mapToArray(body: (Double) -> B): Array<B> {
    val arr = arrayOfNulls<B>(size)
    var i = 0
    for(e in this) arr[i++] = body(e)
    @Suppress("UNCHECKED_CAST")
    return arr as Array<B>
}
