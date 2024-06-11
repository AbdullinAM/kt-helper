@file:Suppress("unused", "DuplicatedCode")

package org.vorpal.research.kthelper.collection

import org.vorpal.research.kthelper.cast
import org.vorpal.research.kthelper.compareTo
import org.vorpal.research.kthelper.div
import org.vorpal.research.kthelper.plus

inline fun <reified N : Number> minOf(vararg numbers: N): N {
    if (numbers.isEmpty()) throw IllegalStateException()
    var min = numbers.first()
    for (i in 1..numbers.lastIndex) {
        if (numbers[i] < min) min = numbers[i]
    }
    return min
}

inline fun <reified N : Number> maxOf(vararg numbers: N): N {
    if (numbers.isEmpty()) throw IllegalStateException()
    var max = numbers.first()
    for (i in 1..numbers.lastIndex) {
        if (numbers[i] > max) max = numbers[i]
    }
    return max
}

inline fun <reified N : Number> minOf(numbers: Collection<N>): N {
    if (numbers.isEmpty()) throw IllegalStateException()
    var min = numbers.first()
    for (num in numbers) {
        if (num < min) min = num
    }
    return min
}

inline fun <reified N : Number> maxOf(numbers: Collection<N>): N {
    if (numbers.isEmpty()) throw IllegalStateException()
    var max = numbers.first()
    for (num in numbers) {
        if (num > max) max = num
    }
    return max
}

inline fun <reified N : Number> Collection<N>.average(): Double {
    if (this.isEmpty()) throw IllegalStateException("Collection is empty")
    var sum = 0.cast<N>()
    for (x in this) {
        sum += x
    }
    return (sum / this.size.cast()).toDouble()
}

inline fun <reified N : Number> Collection<N>.median(): Double {
    if (this.isEmpty()) throw IllegalStateException("Collection is empty")
    val sorted = this.sortedWith { a, b -> a.compareTo(b) }
    return when {
        sorted.size % 2 == 0 -> (sorted[sorted.size / 2] + sorted[sorted.size / 2 - 1]).toDouble() / 2
        else -> sorted[sorted.size / 2].toDouble()
    }
}

inline fun <reified N : Number> Collection<N>.mode(): N {
    if (this.isEmpty()) throw IllegalStateException("Collection is empty")
    val sorted = this.sortedWith { a, b -> a.compareTo(b) }

    var previous = sorted[0]
    var mode = sorted[0]
    var count = 1
    var maxCount = 1

    for (i in 1..<this.size) {
        when (sorted[i]) {
            previous -> count++
            else -> {
                if (count > maxCount) {
                    mode = sorted[i - 1]
                    maxCount = count
                }
                previous = sorted[i]
                count = 1
            }
        }
    }

    return when {
        count > maxCount -> sorted[sorted.lastIndex]
        else -> mode
    }
}

inline fun <reified N : Number> Collection<N>.averageOrNull(): Double? = when {
    this.isEmpty() -> null
    else -> this.average()
}

inline fun <reified N : Number> Collection<N>.averageOrZero(): Double = when {
    this.isEmpty() -> 0.0
    else -> this.average()
}

inline fun <reified N : Number> Collection<N>.medianOrNull(): Double? = when {
    this.isEmpty() -> null
    else -> this.median()
}

inline fun <reified N : Number> Collection<N>.medianOrZero(): Double = when {
    this.isEmpty() -> 0.0
    else -> this.median()
}

inline fun <reified N : Number> Collection<N>.modeOrNull(): N? = when {
    this.isEmpty() -> null
    else -> this.mode()
}

inline fun <reified N : Number> Collection<N>.modeOrZero(): N = when {
    this.isEmpty() -> 0.cast()
    else -> this.mode()
}

inline fun <T, reified N : Number> Collection<T>.averageBy(crossinline selector: (T) -> N): Double {
    if (this.isEmpty()) throw IllegalStateException("Collection is empty")
    var sum = 0.cast<N>()
    for (x in this) {
        sum += selector(x)
    }
    return (sum / this.size.cast()).toDouble()
}

inline fun <T, reified N : Number> Collection<T>.medianBy(crossinline selector: (T) -> N): Double {
    if (this.isEmpty()) throw IllegalStateException("Collection is empty")
    val sorted = this.sortedWith { a, b -> selector(a).compareTo(selector(b)) }
    return when {
        sorted.size % 2 == 0 -> (selector(sorted[sorted.size / 2]) + selector(sorted[sorted.size / 2 - 1])).toDouble() / 2
        else -> selector(sorted[sorted.size / 2]).toDouble()
    }
}

inline fun <T, reified N : Number> Collection<T>.modeBy(crossinline selector: (T) -> N): N {
    if (this.isEmpty()) throw IllegalStateException("Collection is empty")
    val sorted = this.sortedWith { a, b -> selector(a).compareTo(selector(b)) }

    var previous = selector(sorted[0])
    var mode = selector(sorted[0])
    var count = 1
    var maxCount = 1

    for (i in 1..<this.size) {
        when (selector(sorted[i])) {
            previous -> count++
            else -> {
                if (count > maxCount) {
                    mode = selector(sorted[i - 1])
                    maxCount = count
                }
                previous = selector(sorted[i])
                count = 1
            }
        }
    }

    return when {
        count > maxCount -> selector(sorted[sorted.lastIndex])
        else -> mode
    }
}

inline fun <T, reified N : Number> Collection<T>.averageByOrNull(crossinline selector: (T) -> N): Double? = when {
    this.isEmpty() -> null
    else -> this.averageBy(selector)
}

inline fun <T, reified N : Number> Collection<T>.averageByOrZero(crossinline selector: (T) -> N): Double = when {
    this.isEmpty() -> 0.0
    else -> this.averageBy(selector)
}

inline fun <T, reified N : Number> Collection<T>.medianByOrNull(crossinline selector: (T) -> N): Double? = when {
    this.isEmpty() -> null
    else -> this.medianBy(selector)
}

inline fun <T, reified N : Number> Collection<T>.medianByOrZero(crossinline selector: (T) -> N): Double = when {
    this.isEmpty() -> 0.0
    else -> this.medianBy(selector)
}

inline fun <T, reified N : Number> Collection<T>.modeByOrNull(crossinline selector: (T) -> N): N? = when {
    this.isEmpty() -> null
    else -> this.modeBy(selector)
}

inline fun <T, reified N : Number> Collection<T>.modeByOrZero(crossinline selector: (T) -> N): N = when {
    this.isEmpty() -> 0.cast()
    else -> this.modeBy(selector)
}

inline fun <T, reified N : Number> Array<T>.averageBy(crossinline selector: (T) -> N): Double {
    if (this.isEmpty()) throw IllegalStateException("Collection is empty")
    var sum = 0.cast<N>()
    for (x in this) {
        sum += selector(x)
    }
    return (sum / this.size.cast()).toDouble()
}

inline fun <T, reified N : Number> Array<T>.medianBy(crossinline selector: (T) -> N): Double {
    if (this.isEmpty()) throw IllegalStateException("Collection is empty")
    val sorted = this.sortedWith { a, b -> selector(a).compareTo(selector(b)) }
    return when {
        sorted.size % 2 == 0 -> (selector(sorted[sorted.size / 2]) + selector(sorted[sorted.size / 2 - 1])).toDouble() / 2
        else -> selector(sorted[sorted.size / 2]).toDouble()
    }
}

inline fun <T, reified N : Number> Array<T>.modeBy(crossinline selector: (T) -> N): N {
    if (this.isEmpty()) throw IllegalStateException("Collection is empty")
    val sorted = this.sortedWith { a, b -> selector(a).compareTo(selector(b)) }

    var previous = selector(sorted[0])
    var mode = selector(sorted[0])
    var count = 1
    var maxCount = 1

    for (i in 1..<this.size) {
        when (selector(sorted[i])) {
            previous -> count++
            else -> {
                if (count > maxCount) {
                    mode = selector(sorted[i - 1])
                    maxCount = count
                }
                previous = selector(sorted[i])
                count = 1
            }
        }
    }

    return when {
        count > maxCount -> selector(sorted[sorted.lastIndex])
        else -> mode
    }
}

inline fun <T, reified N : Number> Array<T>.averageByOrNull(crossinline selector: (T) -> N): Double? = when {
    this.isEmpty() -> null
    else -> this.averageBy(selector)
}

inline fun <T, reified N : Number> Array<T>.averageByOrZero(crossinline selector: (T) -> N): Double = when {
    this.isEmpty() -> 0.0
    else -> this.averageBy(selector)
}

inline fun <T, reified N : Number> Array<T>.medianByOrNull(crossinline selector: (T) -> N): Double? = when {
    this.isEmpty() -> null
    else -> this.medianBy(selector)
}

inline fun <T, reified N : Number> Array<T>.medianByOrZero(crossinline selector: (T) -> N): Double = when {
    this.isEmpty() -> 0.0
    else -> this.medianBy(selector)
}

inline fun <T, reified N : Number> Array<T>.modeByOrNull(crossinline selector: (T) -> N): N? = when {
    this.isEmpty() -> null
    else -> this.modeBy(selector)
}

inline fun <T, reified N : Number> Array<T>.modeByOrZero(crossinline selector: (T) -> N): N = when {
    this.isEmpty() -> 0.cast()
    else -> this.modeBy(selector)
}

fun IntArray.average(): Double {
    if (this.isEmpty()) throw IllegalStateException("Collection is empty")
    var sum = 0.0
    for (x in this) {
        sum += x
    }
    return sum / this.size
}

fun IntArray.median(): Double {
    if (this.isEmpty()) throw IllegalStateException("Collection is empty")
    val sorted = this.sorted()
    return when {
        sorted.size % 2 == 0 -> (sorted[sorted.size / 2] + sorted[sorted.size / 2 - 1]).toDouble() / 2
        else -> sorted[sorted.size / 2].toDouble()
    }
}

fun IntArray.mode(): Int {
    if (this.isEmpty()) throw IllegalStateException("Collection is empty")
    val sorted = this.sorted()

    var previous = sorted[0]
    var mode = sorted[0]
    var count = 1
    var maxCount = 1

    for (i in 1..<this.size) {
        when (sorted[i]) {
            previous -> count++
            else -> {
                if (count > maxCount) {
                    mode = sorted[i - 1]
                    maxCount = count
                }
                previous = sorted[i]
                count = 1
            }
        }
    }

    return when {
        count > maxCount -> sorted[sorted.lastIndex]
        else -> mode
    }
}

fun IntArray.averageOrNull(): Double? = when {
    this.isEmpty() -> null
    else -> this.average()
}

fun IntArray.averageOrZero(): Double = when {
    this.isEmpty() -> 0.0
    else -> this.average()
}

fun IntArray.medianOrNull(): Double? = when {
    this.isEmpty() -> null
    else -> this.median()
}

fun IntArray.medianOrZero(): Double = when {
    this.isEmpty() -> 0.0
    else -> this.median()
}

fun IntArray.modeOrNull(): Int? = when {
    this.isEmpty() -> null
    else -> this.mode()
}

fun IntArray.modeOrZero(): Int = when {
    this.isEmpty() -> 0
    else -> this.mode()
}


fun ByteArray.average(): Double {
    if (this.isEmpty()) throw IllegalStateException("Collection is empty")
    var sum = 0.0
    for (x in this) {
        sum += x
    }
    return sum / this.size
}

fun ByteArray.median(): Double {
    if (this.isEmpty()) throw IllegalStateException("Collection is empty")
    val sorted = this.sorted()
    return when {
        sorted.size % 2 == 0 -> (sorted[sorted.size / 2] + sorted[sorted.size / 2 - 1]).toDouble() / 2
        else -> sorted[sorted.size / 2].toDouble()
    }
}

fun ByteArray.mode(): Byte {
    if (this.isEmpty()) throw IllegalStateException("Collection is empty")
    val sorted = this.sorted()

    var previous = sorted[0]
    var mode = sorted[0]
    var count = 1
    var maxCount = 1

    for (i in 1..<this.size) {
        when (sorted[i]) {
            previous -> count++
            else -> {
                if (count > maxCount) {
                    mode = sorted[i - 1]
                    maxCount = count
                }
                previous = sorted[i]
                count = 1
            }
        }
    }

    return when {
        count > maxCount -> sorted[sorted.lastIndex]
        else -> mode
    }
}

fun ByteArray.averageOrNull(): Double? = when {
    this.isEmpty() -> null
    else -> this.average()
}

fun ByteArray.averageOrZero(): Double = when {
    this.isEmpty() -> 0.0
    else -> this.average()
}

fun ByteArray.medianOrNull(): Double? = when {
    this.isEmpty() -> null
    else -> this.median()
}

fun ByteArray.medianOrZero(): Double = when {
    this.isEmpty() -> 0.0
    else -> this.median()
}

fun ByteArray.modeOrNull(): Byte? = when {
    this.isEmpty() -> null
    else -> this.mode()
}

fun ByteArray.modeOrZero(): Byte = when {
    this.isEmpty() -> 0
    else -> this.mode()
}


fun ShortArray.average(): Double {
    if (this.isEmpty()) throw IllegalStateException("Collection is empty")
    var sum = 0.0
    for (x in this) {
        sum += x
    }
    return sum / this.size
}

fun ShortArray.median(): Double {
    if (this.isEmpty()) throw IllegalStateException("Collection is empty")
    val sorted = this.sorted()
    return when {
        sorted.size % 2 == 0 -> (sorted[sorted.size / 2] + sorted[sorted.size / 2 - 1]).toDouble() / 2
        else -> sorted[sorted.size / 2].toDouble()
    }
}

fun ShortArray.mode(): Short {
    if (this.isEmpty()) throw IllegalStateException("Collection is empty")
    val sorted = this.sorted()

    var previous = sorted[0]
    var mode = sorted[0]
    var count = 1
    var maxCount = 1

    for (i in 1..<this.size) {
        when (sorted[i]) {
            previous -> count++
            else -> {
                if (count > maxCount) {
                    mode = sorted[i - 1]
                    maxCount = count
                }
                previous = sorted[i]
                count = 1
            }
        }
    }

    return when {
        count > maxCount -> sorted[sorted.lastIndex]
        else -> mode
    }
}

fun ShortArray.averageOrNull(): Double? = when {
    this.isEmpty() -> null
    else -> this.average()
}

fun ShortArray.averageOrZero(): Double = when {
    this.isEmpty() -> 0.0
    else -> this.average()
}

fun ShortArray.medianOrNull(): Double? = when {
    this.isEmpty() -> null
    else -> this.median()
}

fun ShortArray.medianOrZero(): Double = when {
    this.isEmpty() -> 0.0
    else -> this.median()
}

fun ShortArray.modeOrNull(): Short? = when {
    this.isEmpty() -> null
    else -> this.mode()
}

fun ShortArray.modeOrZero(): Short = when {
    this.isEmpty() -> 0
    else -> this.mode()
}


fun LongArray.average(): Double {
    if (this.isEmpty()) throw IllegalStateException("Collection is empty")
    var sum = 0.0
    for (x in this) {
        sum += x
    }
    return sum / this.size
}

fun LongArray.median(): Double {
    if (this.isEmpty()) throw IllegalStateException("Collection is empty")
    val sorted = this.sorted()
    return when {
        sorted.size % 2 == 0 -> (sorted[sorted.size / 2] + sorted[sorted.size / 2 - 1]).toDouble() / 2
        else -> sorted[sorted.size / 2].toDouble()
    }
}

fun LongArray.mode(): Long {
    if (this.isEmpty()) throw IllegalStateException("Collection is empty")
    val sorted = this.sorted()

    var previous = sorted[0]
    var mode = sorted[0]
    var count = 1
    var maxCount = 1

    for (i in 1..<this.size) {
        when (sorted[i]) {
            previous -> count++
            else -> {
                if (count > maxCount) {
                    mode = sorted[i - 1]
                    maxCount = count
                }
                previous = sorted[i]
                count = 1
            }
        }
    }

    return when {
        count > maxCount -> sorted[sorted.lastIndex]
        else -> mode
    }
}

fun LongArray.averageOrNull(): Double? = when {
    this.isEmpty() -> null
    else -> this.average()
}

fun LongArray.averageOrZero(): Double = when {
    this.isEmpty() -> 0.0
    else -> this.average()
}

fun LongArray.medianOrNull(): Double? = when {
    this.isEmpty() -> null
    else -> this.median()
}

fun LongArray.medianOrZero(): Double = when {
    this.isEmpty() -> 0.0
    else -> this.median()
}

fun LongArray.modeOrNull(): Long? = when {
    this.isEmpty() -> null
    else -> this.mode()
}

fun LongArray.modeOrZero(): Long = when {
    this.isEmpty() -> 0
    else -> this.mode()
}


fun DoubleArray.average(): Double {
    if (this.isEmpty()) throw IllegalStateException("Collection is empty")
    var sum = 0.0
    for (x in this) {
        sum += x
    }
    return sum / this.size
}

fun DoubleArray.median(): Double {
    if (this.isEmpty()) throw IllegalStateException("Collection is empty")
    val sorted = this.sorted()
    return when {
        sorted.size % 2 == 0 -> (sorted[sorted.size / 2] + sorted[sorted.size / 2 - 1]) / 2
        else -> sorted[sorted.size / 2]
    }
}

fun DoubleArray.mode(): Double {
    if (this.isEmpty()) throw IllegalStateException("Collection is empty")
    val sorted = this.sorted()

    var previous = sorted[0]
    var mode = sorted[0]
    var count = 1
    var maxCount = 1

    for (i in 1..<this.size) {
        when (sorted[i]) {
            previous -> count++
            else -> {
                if (count > maxCount) {
                    mode = sorted[i - 1]
                    maxCount = count
                }
                previous = sorted[i]
                count = 1
            }
        }
    }

    return when {
        count > maxCount -> sorted[sorted.lastIndex]
        else -> mode
    }
}

fun DoubleArray.averageOrNull(): Double? = when {
    this.isEmpty() -> null
    else -> this.average()
}

fun DoubleArray.averageOrZero(): Double = when {
    this.isEmpty() -> 0.0
    else -> this.average()
}

fun DoubleArray.medianOrNull(): Double? = when {
    this.isEmpty() -> null
    else -> this.median()
}

fun DoubleArray.medianOrZero(): Double = when {
    this.isEmpty() -> 0.0
    else -> this.median()
}

fun DoubleArray.modeOrNull(): Double? = when {
    this.isEmpty() -> null
    else -> this.mode()
}

fun DoubleArray.modeOrZero(): Double = when {
    this.isEmpty() -> 0.0
    else -> this.mode()
}


fun FloatArray.average(): Double {
    if (this.isEmpty()) throw IllegalStateException("Collection is empty")
    var sum = 0.0
    for (x in this) {
        sum += x
    }
    return sum / this.size
}

fun FloatArray.median(): Double {
    if (this.isEmpty()) throw IllegalStateException("Collection is empty")
    val sorted = this.sorted()
    return when {
        sorted.size % 2 == 0 -> (sorted[sorted.size / 2] + sorted[sorted.size / 2 - 1]).toDouble() / 2
        else -> sorted[sorted.size / 2].toDouble()
    }
}

fun FloatArray.mode(): Float {
    if (this.isEmpty()) throw IllegalStateException("Collection is empty")
    val sorted = this.sorted()

    var previous = sorted[0]
    var mode = sorted[0]
    var count = 1
    var maxCount = 1

    for (i in 1..<this.size) {
        when (sorted[i]) {
            previous -> count++
            else -> {
                if (count > maxCount) {
                    mode = sorted[i - 1]
                    maxCount = count
                }
                previous = sorted[i]
                count = 1
            }
        }
    }

    return when {
        count > maxCount -> sorted[sorted.lastIndex]
        else -> mode
    }
}

fun FloatArray.averageOrNull(): Double? = when {
    this.isEmpty() -> null
    else -> this.average()
}

fun FloatArray.averageOrZero(): Double = when {
    this.isEmpty() -> 0.0
    else -> this.average()
}

fun FloatArray.medianOrNull(): Double? = when {
    this.isEmpty() -> null
    else -> this.median()
}

fun FloatArray.medianOrZero(): Double = when {
    this.isEmpty() -> 0.0
    else -> this.median()
}

fun FloatArray.modeOrNull(): Float? = when {
    this.isEmpty() -> null
    else -> this.mode()
}

fun FloatArray.modeOrZero(): Float = when {
    this.isEmpty() -> 0.0f
    else -> this.mode()
}
