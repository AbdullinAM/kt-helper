package org.jetbrains.research.kthelper.collection

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
