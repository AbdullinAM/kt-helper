@file:Suppress("unused", "UseWithIndex", "NOTHING_TO_INLINE")

package org.vorpal.research.kthelper.collection

import java.util.*
import java.util.stream.Stream

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
    for (e in this) arr[i++] = body(e)
    @Suppress("UNCHECKED_CAST")
    return arr as Array<B>
}

inline fun <A> Collection<A>.mapToBooleanArray(body: (A) -> Boolean): BooleanArray {
    val arr = BooleanArray(size)
    var i = 0
    for (e in this) arr[i++] = body(e)
    return arr
}

inline fun <A> Collection<A>.mapToCharArray(body: (A) -> Char): CharArray {
    val arr = CharArray(size)
    var i = 0
    for (e in this) arr[i++] = body(e)
    return arr
}

inline fun <A> Collection<A>.mapToByteArray(body: (A) -> Byte): ByteArray {
    val arr = ByteArray(size)
    var i = 0
    for (e in this) arr[i++] = body(e)
    return arr
}

inline fun <A> Collection<A>.mapToShortArray(body: (A) -> Short): ShortArray {
    val arr = ShortArray(size)
    var i = 0
    for (e in this) arr[i++] = body(e)
    return arr
}

inline fun <A> Collection<A>.mapToIntArray(body: (A) -> Int): IntArray {
    val arr = IntArray(size)
    var i = 0
    for (e in this) arr[i++] = body(e)
    return arr
}

inline fun <A> Collection<A>.mapToLongArray(body: (A) -> Long): LongArray {
    val arr = LongArray(size)
    var i = 0
    for (e in this) arr[i++] = body(e)
    return arr
}

inline fun <A> Collection<A>.mapToFloatArray(body: (A) -> Float): FloatArray {
    val arr = FloatArray(size)
    var i = 0
    for (e in this) arr[i++] = body(e)
    return arr
}

inline fun <A> Collection<A>.mapToDoubleArray(body: (A) -> Double): DoubleArray {
    val arr = DoubleArray(size)
    var i = 0
    for (e in this) arr[i++] = body(e)
    return arr
}

inline fun <K, V, reified B> Map<K,V>.mapToArray(body: (K, V) -> B): Array<B> {
    val arr = arrayOfNulls<B>(size)
    var i = 0
    for ((k, v) in this) arr[i++] = body(k, v)
    @Suppress("UNCHECKED_CAST")
    return arr as Array<B>
}

inline fun <K, V, reified B> Map<K,V>.mapToBooleanArray(body: (K, V) -> Boolean): BooleanArray {
    val arr = BooleanArray(size)
    var i = 0
    for ((k, v) in this) arr[i++] = body(k, v)
    return arr
}

inline fun <K, V, reified B> Map<K,V>.mapToCharArray(body: (K, V) -> Char): CharArray {
    val arr = CharArray(size)
    var i = 0
    for ((k, v) in this) arr[i++] = body(k, v)
    return arr
}

inline fun <K, V, reified B> Map<K,V>.mapToByteArray(body: (K, V) -> Byte): ByteArray {
    val arr = ByteArray(size)
    var i = 0
    for ((k, v) in this) arr[i++] = body(k, v)
    return arr
}

inline fun <K, V, reified B> Map<K,V>.mapToShortArray(body: (K, V) -> Short): ShortArray {
    val arr = ShortArray(size)
    var i = 0
    for ((k, v) in this) arr[i++] = body(k, v)
    return arr
}

inline fun <K, V, reified B> Map<K,V>.mapToIntArray(body: (K, V) -> Int): IntArray {
    val arr = IntArray(size)
    var i = 0
    for ((k, v) in this) arr[i++] = body(k, v)
    return arr
}

inline fun <K, V, reified B> Map<K,V>.mapToLongArray(body: (K, V) -> Long): LongArray {
    val arr = LongArray(size)
    var i = 0
    for ((k, v) in this) arr[i++] = body(k, v)
    return arr
}

inline fun <K, V, reified B> Map<K,V>.mapToFloatArray(body: (K, V) -> Float): FloatArray {
    val arr = FloatArray(size)
    var i = 0
    for ((k, v) in this) arr[i++] = body(k, v)
    return arr
}

inline fun <K, V, reified B> Map<K,V>.mapToDoubleArray(body: (K, V) -> Double): DoubleArray {
    val arr = DoubleArray(size)
    var i = 0
    for ((k, v) in this) arr[i++] = body(k, v)
    return arr
}

inline fun <A, reified B> Array<A>.mapToArray(body: (A) -> B): Array<B> {
    val arr = arrayOfNulls<B>(size)
    var i = 0
    for (e in this) arr[i++] = body(e)
    @Suppress("UNCHECKED_CAST")
    return arr as Array<B>
}

inline fun <reified B> BooleanArray.mapToArray(body: (Boolean) -> B): Array<B> {
    val arr = arrayOfNulls<B>(size)
    var i = 0
    for (e in this) arr[i++] = body(e)
    @Suppress("UNCHECKED_CAST")
    return arr as Array<B>
}

inline fun <reified B> CharArray.mapToArray(body: (Char) -> B): Array<B> {
    val arr = arrayOfNulls<B>(size)
    var i = 0
    for (e in this) arr[i++] = body(e)
    @Suppress("UNCHECKED_CAST")
    return arr as Array<B>
}

inline fun <reified B> ShortArray.mapToArray(body: (Short) -> B): Array<B> {
    val arr = arrayOfNulls<B>(size)
    var i = 0
    for (e in this) arr[i++] = body(e)
    @Suppress("UNCHECKED_CAST")
    return arr as Array<B>
}

inline fun <reified B> ByteArray.mapToArray(body: (Byte) -> B): Array<B> {
    val arr = arrayOfNulls<B>(size)
    var i = 0
    for (e in this) arr[i++] = body(e)
    @Suppress("UNCHECKED_CAST")
    return arr as Array<B>
}

inline fun <reified B> IntArray.mapToArray(body: (Int) -> B): Array<B> {
    val arr = arrayOfNulls<B>(size)
    var i = 0
    for (e in this) arr[i++] = body(e)
    @Suppress("UNCHECKED_CAST")
    return arr as Array<B>
}

inline fun <reified B> LongArray.mapToArray(body: (Long) -> B): Array<B> {
    val arr = arrayOfNulls<B>(size)
    var i = 0
    for (e in this) arr[i++] = body(e)
    @Suppress("UNCHECKED_CAST")
    return arr as Array<B>
}

inline fun <reified B> FloatArray.mapToArray(body: (Float) -> B): Array<B> {
    val arr = arrayOfNulls<B>(size)
    var i = 0
    for (e in this) arr[i++] = body(e)
    @Suppress("UNCHECKED_CAST")
    return arr as Array<B>
}

inline fun <reified B> DoubleArray.mapToArray(body: (Double) -> B): Array<B> {
    val arr = arrayOfNulls<B>(size)
    var i = 0
    for (e in this) arr[i++] = body(e)
    @Suppress("UNCHECKED_CAST")
    return arr as Array<B>
}


inline fun <A, reified B> Collection<A>.mapIndexedToArray(body: (index: Int, element: A) -> B): Array<B> {
    val arr = arrayOfNulls<B>(size)
    var i = 0
    for (e in this) {
        arr[i] = body(i, e)
        i++
    }
    @Suppress("UNCHECKED_CAST")
    return arr as Array<B>
}

inline fun <A> Collection<A>.mapIndexedToBooleanArray(body: (index: Int, element: A) -> Boolean): BooleanArray {
    val arr = BooleanArray(size)
    var i = 0
    for (e in this) {
        arr[i] = body(i, e)
        i++
    }
    return arr
}

inline fun <A> Collection<A>.mapIndexedToCharArray(body: (index: Int, element: A) -> Char): CharArray {
    val arr = CharArray(size)
    var i = 0
    for (e in this) {
        arr[i] = body(i, e)
        i++
    }
    return arr
}

inline fun <A> Collection<A>.mapIndexedToByteArray(body: (index: Int, element: A) -> Byte): ByteArray {
    val arr = ByteArray(size)
    var i = 0
    for (e in this) {
        arr[i] = body(i, e)
        i++
    }
    return arr
}

inline fun <A> Collection<A>.mapIndexedToShortArray(body: (index: Int, element: A) -> Short): ShortArray {
    val arr = ShortArray(size)
    var i = 0
    for (e in this) {
        arr[i] = body(i, e)
        i++
    }
    return arr
}

inline fun <A> Collection<A>.mapIndexedToIntArray(body: (index: Int, element: A) -> Int): IntArray {
    val arr = IntArray(size)
    var i = 0
    for (e in this) {
        arr[i] = body(i, e)
        i++
    }
    return arr
}

inline fun <A> Collection<A>.mapIndexedToLongArray(body: (index: Int, element: A) -> Long): LongArray {
    val arr = LongArray(size)
    var i = 0
    for (e in this) {
        arr[i] = body(i, e)
        i++
    }
    return arr
}

inline fun <A> Collection<A>.mapIndexedToFloatArray(body: (index: Int, element: A) -> Float): FloatArray {
    val arr = FloatArray(size)
    var i = 0
    for (e in this) {
        arr[i] = body(i, e)
        i++
    }
    return arr
}

inline fun <A> Collection<A>.mapIndexedToDoubleArray(body: (index: Int, element: A) -> Double): DoubleArray {
    val arr = DoubleArray(size)
    var i = 0
    for (e in this) {
        arr[i] = body(i, e)
        i++
    }
    return arr
}

inline fun <A, reified B> Array<A>.mapIndexedToArray(body: (index: Int, element: A) -> B): Array<B> {
    val arr = arrayOfNulls<B>(size)
    var i = 0
    for (e in this) {
        arr[i] = body(i, e)
        i++
    }
    @Suppress("UNCHECKED_CAST")
    return arr as Array<B>
}

inline fun <K, V, reified B> Map<K,V>.mapIndexedToArray(body: (index: Int, key: K, value: V) -> B): Array<B> {
    val arr = arrayOfNulls<B>(size)
    var i = 0
    for ((k, v) in this) arr[i++] = body(i, k, v)
    @Suppress("UNCHECKED_CAST")
    return arr as Array<B>
}

inline fun <K, V, reified B> Map<K,V>.mapIndexedToBooleanArray(body: (index: Int, key: K, value: V) -> Boolean): BooleanArray {
    val arr = BooleanArray(size)
    var i = 0
    for ((k, v) in this) arr[i++] = body(i, k, v)
    return arr
}

inline fun <K, V, reified B> Map<K,V>.mapIndexedToCharArray(body: (index: Int, key: K, value: V) -> Char): CharArray {
    val arr = CharArray(size)
    var i = 0
    for ((k, v) in this) arr[i++] = body(i, k, v)
    return arr
}

inline fun <K, V, reified B> Map<K,V>.mapIndexedToByteArray(body: (index: Int, key: K, value: V) -> Byte): ByteArray {
    val arr = ByteArray(size)
    var i = 0
    for ((k, v) in this) arr[i++] = body(i, k, v)
    return arr
}

inline fun <K, V, reified B> Map<K,V>.mapIndexedToShortArray(body: (index: Int, key: K, value: V) -> Short): ShortArray {
    val arr = ShortArray(size)
    var i = 0
    for ((k, v) in this) arr[i++] = body(i, k, v)
    return arr
}

inline fun <K, V, reified B> Map<K,V>.mapIndexedToIntArray(body: (index: Int, key: K, value: V) -> Int): IntArray {
    val arr = IntArray(size)
    var i = 0
    for ((k, v) in this) arr[i++] = body(i, k, v)
    return arr
}

inline fun <K, V, reified B> Map<K,V>.mapIndexedToLongArray(body: (index: Int, key: K, value: V) -> Long): LongArray {
    val arr = LongArray(size)
    var i = 0
    for ((k, v) in this) arr[i++] = body(i, k, v)
    return arr
}

inline fun <K, V, reified B> Map<K,V>.mapIndexedToFloatArray(body: (index: Int, key: K, value: V) -> Float): FloatArray {
    val arr = FloatArray(size)
    var i = 0
    for ((k, v) in this) arr[i++] = body(i, k, v)
    return arr
}

inline fun <K, V, reified B> Map<K,V>.mapIndexedToDoubleArray(body: (index: Int, key: K, value: V) -> Double): DoubleArray {
    val arr = DoubleArray(size)
    var i = 0
    for ((k, v) in this) arr[i++] = body(i, k, v)
    return arr
}

inline fun <reified B> BooleanArray.mapIndexedToArray(body: (index: Int, element: Boolean) -> B): Array<B> {
    val arr = arrayOfNulls<B>(size)
    var i = 0
    for (e in this) {
        arr[i] = body(i, e)
        i++
    }
    @Suppress("UNCHECKED_CAST")
    return arr as Array<B>
}

inline fun <reified B> CharArray.mapIndexedToArray(body: (index: Int, element: Char) -> B): Array<B> {
    val arr = arrayOfNulls<B>(size)
    var i = 0
    for (e in this) {
        arr[i] = body(i, e)
        i++
    }
    @Suppress("UNCHECKED_CAST")
    return arr as Array<B>
}

inline fun <reified B> ShortArray.mapIndexedToArray(body: (index: Int, element: Short) -> B): Array<B> {
    val arr = arrayOfNulls<B>(size)
    var i = 0
    for (e in this) {
        arr[i] = body(i, e)
        i++
    }
    @Suppress("UNCHECKED_CAST")
    return arr as Array<B>
}

inline fun <reified B> ByteArray.mapIndexedToArray(body: (index: Int, element: Byte) -> B): Array<B> {
    val arr = arrayOfNulls<B>(size)
    var i = 0
    for (e in this) {
        arr[i] = body(i, e)
        i++
    }
    @Suppress("UNCHECKED_CAST")
    return arr as Array<B>
}

inline fun <reified B> IntArray.mapIndexedToArray(body: (index: Int, element: Int) -> B): Array<B> {
    val arr = arrayOfNulls<B>(size)
    var i = 0
    for (e in this) {
        arr[i] = body(i, e)
        i++
    }
    @Suppress("UNCHECKED_CAST")
    return arr as Array<B>
}

inline fun <reified B> LongArray.mapIndexedToArray(body: (index: Int, element: Long) -> B): Array<B> {
    val arr = arrayOfNulls<B>(size)
    var i = 0
    for (e in this) {
        arr[i] = body(i, e)
        i++
    }
    @Suppress("UNCHECKED_CAST")
    return arr as Array<B>
}

inline fun <reified B> FloatArray.mapIndexedToArray(body: (index: Int, element: Float) -> B): Array<B> {
    val arr = arrayOfNulls<B>(size)
    var i = 0
    for (e in this) {
        arr[i] = body(i, e)
        i++
    }
    @Suppress("UNCHECKED_CAST")
    return arr as Array<B>
}

inline fun <reified B> DoubleArray.mapIndexedToArray(body: (index: Int, element: Double) -> B): Array<B> {
    val arr = arrayOfNulls<B>(size)
    var i = 0
    for (e in this) {
        arr[i] = body(i, e)
        i++
    }
    @Suppress("UNCHECKED_CAST")
    return arr as Array<B>
}


inline fun <A, reified B> Sequence<A>.mapToArray(body: (A) -> B): Array<B> {
    val arr = arrayOfNulls<B>(count())
    var i = 0
    for (e in this) arr[i++] = body(e)
    @Suppress("UNCHECKED_CAST")
    return arr as Array<B>
}

inline fun <A> Sequence<A>.mapToBooleanArray(body: (A) -> Boolean): BooleanArray {
    val arr = BooleanArray(count())
    var i = 0
    for (e in this) arr[i++] = body(e)
    return arr
}

inline fun <A> Sequence<A>.mapToCharArray(body: (A) -> Char): CharArray {
    val arr = CharArray(count())
    var i = 0
    for (e in this) arr[i++] = body(e)
    return arr
}

inline fun <A> Sequence<A>.mapToByteArray(body: (A) -> Byte): ByteArray {
    val arr = ByteArray(count())
    var i = 0
    for (e in this) arr[i++] = body(e)
    return arr
}

inline fun <A> Sequence<A>.mapToShortArray(body: (A) -> Short): ShortArray {
    val arr = ShortArray(count())
    var i = 0
    for (e in this) arr[i++] = body(e)
    return arr
}

inline fun <A> Sequence<A>.mapToIntArray(body: (A) -> Int): IntArray {
    val arr = IntArray(count())
    var i = 0
    for (e in this) arr[i++] = body(e)
    return arr
}

inline fun <A> Sequence<A>.mapToLongArray(body: (A) -> Long): LongArray {
    val arr = LongArray(count())
    var i = 0
    for (e in this) arr[i++] = body(e)
    return arr
}

inline fun <A> Sequence<A>.mapToFloatArray(body: (A) -> Float): FloatArray {
    val arr = FloatArray(count())
    var i = 0
    for (e in this) arr[i++] = body(e)
    return arr
}

inline fun <A> Sequence<A>.mapToDoubleArray(body: (A) -> Double): DoubleArray {
    val arr = DoubleArray(count())
    var i = 0
    for (e in this) arr[i++] = body(e)
    return arr
}

inline fun <A, reified B> Sequence<A>.mapIndexedToArray(body: (index: Int, element: A) -> B): Array<B> {
    val arr = arrayOfNulls<B>(count())
    var i = 0
    for (e in this) {
        arr[i] = body(i, e)
        i++
    }
    @Suppress("UNCHECKED_CAST")
    return arr as Array<B>
}

inline fun <A> Sequence<A>.mapIndexedToBooleanArray(body: (index: Int, element: A) -> Boolean): BooleanArray {
    val arr = BooleanArray(count())
    var i = 0
    for (e in this) {
        arr[i] = body(i, e)
        i++
    }
    return arr
}

inline fun <A> Sequence<A>.mapIndexedToCharArray(body: (index: Int, element: A) -> Char): CharArray {
    val arr = CharArray(count())
    var i = 0
    for (e in this) {
        arr[i] = body(i, e)
        i++
    }
    return arr
}

inline fun <A> Sequence<A>.mapIndexedToByteArray(body: (index: Int, element: A) -> Byte): ByteArray {
    val arr = ByteArray(count())
    var i = 0
    for (e in this) {
        arr[i] = body(i, e)
        i++
    }
    return arr
}

inline fun <A> Sequence<A>.mapIndexedToShortArray(body: (index: Int, element: A) -> Short): ShortArray {
    val arr = ShortArray(count())
    var i = 0
    for (e in this) {
        arr[i] = body(i, e)
        i++
    }
    return arr
}

inline fun <A> Sequence<A>.mapIndexedToIntArray(body: (index: Int, element: A) -> Int): IntArray {
    val arr = IntArray(count())
    var i = 0
    for (e in this) {
        arr[i] = body(i, e)
        i++
    }
    return arr
}

inline fun <A> Sequence<A>.mapIndexedToLongArray(body: (index: Int, element: A) -> Long): LongArray {
    val arr = LongArray(count())
    var i = 0
    for (e in this) {
        arr[i] = body(i, e)
        i++
    }
    return arr
}

inline fun <A> Sequence<A>.mapIndexedToFloatArray(body: (index: Int, element: A) -> Float): FloatArray {
    val arr = FloatArray(count())
    var i = 0
    for (e in this) {
        arr[i] = body(i, e)
        i++
    }
    return arr
}

inline fun <A> Sequence<A>.mapIndexedToDoubleArray(body: (index: Int, element: A) -> Double): DoubleArray {
    val arr = DoubleArray(count())
    var i = 0
    for (e in this) {
        arr[i] = body(i, e)
        i++
    }
    return arr
}


inline fun <A, reified B> Stream<A>.mapToArray(body: (A) -> B): Array<B> {
    val arr = arrayOfNulls<B>(count().toInt())
    var i = 0
    for (e in this) arr[i++] = body(e)
    @Suppress("UNCHECKED_CAST")
    return arr as Array<B>
}

inline fun <A> Stream<A>.mapToBooleanArray(body: (A) -> Boolean): BooleanArray {
    val arr = BooleanArray(count().toInt())
    var i = 0
    for (e in this) arr[i++] = body(e)
    return arr
}

inline fun <A> Stream<A>.mapToCharArray(body: (A) -> Char): CharArray {
    val arr = CharArray(count().toInt())
    var i = 0
    for (e in this) arr[i++] = body(e)
    return arr
}

inline fun <A> Stream<A>.mapToByteArray(body: (A) -> Byte): ByteArray {
    val arr = ByteArray(count().toInt())
    var i = 0
    for (e in this) arr[i++] = body(e)
    return arr
}

inline fun <A> Stream<A>.mapToShortArray(body: (A) -> Short): ShortArray {
    val arr = ShortArray(count().toInt())
    var i = 0
    for (e in this) arr[i++] = body(e)
    return arr
}

inline fun <A> Stream<A>.mapToIntArray(body: (A) -> Int): IntArray {
    val arr = IntArray(count().toInt())
    var i = 0
    for (e in this) arr[i++] = body(e)
    return arr
}

inline fun <A> Stream<A>.mapToLongArray(body: (A) -> Long): LongArray {
    val arr = LongArray(count().toInt())
    var i = 0
    for (e in this) arr[i++] = body(e)
    return arr
}

inline fun <A> Stream<A>.mapToFloatArray(body: (A) -> Float): FloatArray {
    val arr = FloatArray(count().toInt())
    var i = 0
    for (e in this) arr[i++] = body(e)
    return arr
}

inline fun <A> Stream<A>.mapToDoubleArray(body: (A) -> Double): DoubleArray {
    val arr = DoubleArray(count().toInt())
    var i = 0
    for (e in this) arr[i++] = body(e)
    return arr
}

inline fun <A, reified B> Stream<A>.mapIndexedToArray(body: (index: Int, element: A) -> B): Array<B> {
    val arr = arrayOfNulls<B>(count().toInt())
    var i = 0
    for (e in this) {
        arr[i] = body(i, e)
        i++
    }
    @Suppress("UNCHECKED_CAST")
    return arr as Array<B>
}

inline fun <A> Stream<A>.mapIndexedToBooleanArray(body: (index: Int, element: A) -> Boolean): BooleanArray {
    val arr = BooleanArray(count().toInt())
    var i = 0
    for (e in this) {
        arr[i] = body(i, e)
        i++
    }
    return arr
}

inline fun <A> Stream<A>.mapIndexedToCharArray(body: (index: Int, element: A) -> Char): CharArray {
    val arr = CharArray(count().toInt())
    var i = 0
    for (e in this) {
        arr[i] = body(i, e)
        i++
    }
    return arr
}

inline fun <A> Stream<A>.mapIndexedToByteArray(body: (index: Int, element: A) -> Byte): ByteArray {
    val arr = ByteArray(count().toInt())
    var i = 0
    for (e in this) {
        arr[i] = body(i, e)
        i++
    }
    return arr
}

inline fun <A> Stream<A>.mapIndexedToShortArray(body: (index: Int, element: A) -> Short): ShortArray {
    val arr = ShortArray(count().toInt())
    var i = 0
    for (e in this) {
        arr[i] = body(i, e)
        i++
    }
    return arr
}

inline fun <A> Stream<A>.mapIndexedToIntArray(body: (index: Int, element: A) -> Int): IntArray {
    val arr = IntArray(count().toInt())
    var i = 0
    for (e in this) {
        arr[i] = body(i, e)
        i++
    }
    return arr
}

inline fun <A> Stream<A>.mapIndexedToLongArray(body: (index: Int, element: A) -> Long): LongArray {
    val arr = LongArray(count().toInt())
    var i = 0
    for (e in this) {
        arr[i] = body(i, e)
        i++
    }
    return arr
}

inline fun <A> Stream<A>.mapIndexedToFloatArray(body: (index: Int, element: A) -> Float): FloatArray {
    val arr = FloatArray(count().toInt())
    var i = 0
    for (e in this) {
        arr[i] = body(i, e)
        i++
    }
    return arr
}

inline fun <A> Stream<A>.mapIndexedToDoubleArray(body: (index: Int, element: A) -> Double): DoubleArray {
    val arr = DoubleArray(count().toInt())
    var i = 0
    for (e in this) {
        arr[i] = body(i, e)
        i++
    }
    return arr
}

inline fun <A, K, V, R : MutableMap<K, V>> Collection<A>.mapTo(result: R, body: (A) -> Pair<K, V>?): R {
    for (element in this) {
        val transformed = body(element) ?: continue
        result += transformed
    }
    return result
}

inline fun <A, K, V, R : MutableMap<K, V>> Collection<A>.mapNotNullTo(result: R, body: (A) -> Pair<K, V>?): R {
    for (element in this) {
        val transformed = body(element) ?: continue
        result += transformed
    }
    return result
}

inline fun <A, K, V, R : MutableMap<K, V>> Collection<A>.mapIndexedTo(result: R, body: (Int, A) -> Pair<K, V>?): R {
    for (element in this.withIndex()) {
        val transformed = body(element.index, element.value) ?: continue
        result += transformed
    }
    return result
}

inline fun <A, K, V, R : MutableMap<K, V>> Collection<A>.mapIndexedNotNullTo(
    result: R,
    body: (Int, A) -> Pair<K, V>?
): R {
    for (element in this.withIndex()) {
        val transformed = body(element.index, element.value) ?: continue
        result += transformed
    }
    return result
}

inline fun <A, B> Iterable<A>.zipToMap(that: Iterable<B>): Map<A, B> {
    val result = mutableMapOf<A, B>()
    val thisIt = this.iterator()
    val thatIt = that.iterator()
    while (thisIt.hasNext() && thatIt.hasNext()) {
        result[thisIt.next()] = thatIt.next()
    }
    return result
}

inline fun <A, B, C, D> Iterable<A>.zipTo(that: Iterable<B>, transform: (A, B) -> Pair<C, D>): Map<C, D> {
    val result = mutableMapOf<C, D>()
    val thisIt = this.iterator()
    val thatIt = that.iterator()
    while (thisIt.hasNext() && thatIt.hasNext()) {
        result += transform(thisIt.next(), thatIt.next())
    }
    return result
}

inline fun <A, B, R : MutableMap<A, B>> Iterable<A>.zipTo(that: Iterable<B>, result: R): R {
    val thisIt = this.iterator()
    val thatIt = that.iterator()
    while (thisIt.hasNext() && thatIt.hasNext()) {
        result[thisIt.next()] = thatIt.next()
    }
    return result
}

inline fun <A, B, C, D, R : MutableMap<C, D>> Iterable<A>.zipTo(
    that: Iterable<B>,
    result: R,
    transform: (A, B) -> Pair<C, D>
): R {
    val thisIt = this.iterator()
    val thatIt = that.iterator()
    while (thisIt.hasNext() && thatIt.hasNext()) {
        result += transform(thisIt.next(), thatIt.next())
    }
    return result
}

inline fun <A, B, R, C : MutableCollection<R>> Iterable<A>.zipTo(that: Iterable<B>, to: C, transform: (A, B) -> R): C {
    val thisIt = this.iterator()
    val thatIt = that.iterator()
    while (thisIt.hasNext() && thatIt.hasNext()) {
        to.add(transform(thisIt.next(), thatIt.next()))
    }
    return to
}


fun <T> Iterable<T>.elementAt(index: UInt): T = elementAt(index.toInt())
inline fun <T> List<T>.elementAt(index: UInt): T = get(index.toInt())
fun <T> Iterable<T>.elementAtOrElse(index: UInt, defaultValue: (Int) -> T): T =
    elementAtOrElse(index.toInt(), defaultValue)

inline fun <T> List<T>.elementAtOrElse(index: UInt, defaultValue: (Int) -> T): T =
    elementAtOrElse(index.toInt(), defaultValue)

fun <T> Iterable<T>.elementAtOrNull(index: UInt): T? = elementAtOrNull(index.toInt())

inline fun <T> List<T>.elementAtOrNull(index: UInt): T? = elementAtOrNull(index.toInt())

inline fun <T> List<T>.getOrElse(index: UInt, defaultValue: (Int) -> T): T = getOrElse(index.toInt(), defaultValue)

fun <T> List<T>.getOrNull(index: UInt): T? = getOrNull(index.toInt())

fun <T> Iterable<T>.drop(n: UInt): List<T> = drop(n.toInt())

fun <T> List<T>.dropLast(n: UInt): List<T> = dropLast(n.toInt())

fun <T> Iterable<T>.take(n: UInt): List<T> = take(n.toInt())

fun <T> List<T>.takeLast(n: UInt): List<T> = takeLast(n.toInt())

fun <T> Iterable<T>.chunked(size: UInt): List<List<T>> = chunked(size.toInt())

fun <T, R> Iterable<T>.chunked(size: UInt, transform: (List<T>) -> R): List<R> = chunked(size.toInt(), transform)

fun <T> Iterable<T>.windowed(size: UInt, step: UInt = 1U, partialWindows: Boolean = false): List<List<T>> =
    windowed(size.toInt(), step.toInt(), partialWindows)

fun <T, R> Iterable<T>.windowed(
    size: UInt,
    step: UInt = 1U,
    partialWindows: Boolean = false,
    transform: (List<T>) -> R
): List<R> =
    windowed(size.toInt(), step.toInt(), partialWindows, transform)
