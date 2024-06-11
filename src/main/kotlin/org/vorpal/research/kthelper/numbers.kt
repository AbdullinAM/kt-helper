@file:Suppress("unused")

package org.vorpal.research.kthelper

import org.vorpal.research.kthelper.assert.unreachable
import kotlin.reflect.KClass

fun Boolean.toInt(): Int = if (this) 1 else 0
fun Int.toBoolean(): Boolean = this != 0
fun Number.toBoolean(): Boolean = toInt().toBoolean()

val Boolean?.orFalse: Boolean get() = this ?: false
val Boolean?.orTrue: Boolean get() = this ?: true

fun Number.cast(type: KClass<*>): Any = when (type) {
    Byte::class -> toByte()
    Short::class -> toShort()
    Int::class -> toInt()
    Long::class -> toLong()
    Float::class -> toFloat()
    Double::class -> toDouble()
    else -> throw IllegalStateException("Unsupported number type")
}

inline fun <reified T> Number.cast() = cast(T::class) as T

inline operator fun <reified N : Number> N.plus(other: N): N = when (this) {
    is Long -> this.toLong() + other.toLong()
    is Int -> this.toInt() + other.toInt()
    is Short -> this.toShort() + other.toShort()
    is Byte -> this.toByte() + other.toByte()
    is Double -> this.toDouble() + other.toDouble()
    is Float -> this.toFloat() + other.toFloat()
    else -> unreachable("Unknown numeric type")
}.cast()

inline operator fun <reified N : Number> N.minus(other: N): N = when (this) {
    is Long -> this.toLong() - other.toLong()
    is Int -> this.toInt() - other.toInt()
    is Short -> this.toShort() - other.toShort()
    is Byte -> this.toByte() - other.toByte()
    is Double -> this.toDouble() - other.toDouble()
    is Float -> this.toFloat() - other.toFloat()
    else -> unreachable("Unknown numeric type")
}.cast()

inline operator fun <reified N : Number> N.times(other: N): N = when (this) {
    is Long -> this.toLong() * other.toLong()
    is Int -> this.toInt() * other.toInt()
    is Short -> this.toShort() * other.toShort()
    is Byte -> this.toByte() * other.toByte()
    is Double -> this.toDouble() * other.toDouble()
    is Float -> this.toFloat() * other.toFloat()
    else -> unreachable("Unknown numeric type")
}.cast()

inline operator fun <reified N : Number> N.div(other: N): N = when (this) {
    is Long -> this.toLong() / other.toLong()
    is Int -> this.toInt() / other.toInt()
    is Short -> this.toShort() / other.toShort()
    is Byte -> this.toByte() / other.toByte()
    is Double -> this.toDouble() / other.toDouble()
    is Float -> this.toFloat() / other.toFloat()
    else -> unreachable("Unknown numeric type")
}.cast()

inline operator fun <reified N : Number> N.rem(other: N): N = when (this) {
    is Long -> this.toLong() % other.toLong()
    is Int -> this.toInt() % other.toInt()
    is Short -> this.toShort() % other.toShort()
    is Byte -> this.toByte() % other.toByte()
    is Double -> this.toDouble() % other.toDouble()
    is Float -> this.toFloat() % other.toFloat()
    else -> unreachable("Unknown numeric type")
}.cast()

inline operator fun <reified N : Number> N.unaryMinus(): N = when (this) {
    is Long -> this.toLong().unaryMinus()
    is Int -> this.toInt().unaryMinus()
    is Short -> this.toShort().unaryMinus()
    is Byte -> this.toByte().unaryMinus()
    is Double -> this.toDouble().unaryMinus()
    is Float -> this.toFloat().unaryMinus()
    else -> unreachable("Unknown numeric type")
}.cast()

inline operator fun <reified N : Number> N.compareTo(other: N): Int = when (this) {
    is Long -> this.toLong().compareTo(other.toLong())
    is Int -> this.toInt().compareTo(other.toInt())
    is Short -> this.toShort().compareTo(other.toShort())
    is Byte -> this.toByte().compareTo(other.toByte())
    is Double -> this.toDouble().compareTo(other.toDouble())
    is Float -> this.toFloat().compareTo(other.toFloat())
    else -> unreachable("Unknown numeric type")
}

inline infix fun <reified N : Number> N.shl(bits: Int): N = when (this) {
    is Long -> this.toLong().shl(bits)
    is Int -> this.toInt().shl(bits)
    is Short -> this.toInt().shl(bits).toShort()
    is Byte -> this.toInt().shl(bits).toByte()
    else -> unreachable("Unknown numeric type")
}.cast()

inline infix fun <reified N : Number> N.shr(bits: Int): N = when (this) {
    is Long -> this.toLong().shr(bits)
    is Int -> this.toInt().shr(bits)
    is Short -> this.toInt().shr(bits).toShort()
    is Byte -> this.toInt().shr(bits).toByte()
    else -> unreachable("Unknown numeric type")
}.cast()

inline infix fun <reified N : Number> N.ushr(bits: Int): N = when (this) {
    is Long -> this.toLong().ushr(bits)
    is Int -> this.toInt().ushr(bits)
    is Short -> this.toInt().ushr(bits).toShort()
    is Byte -> this.toInt().ushr(bits).toByte()
    else -> unreachable("Unknown numeric type")
}.cast()

inline infix fun <reified N : Number> N.and(other: N): N = when (this) {
    is Long -> this.toLong() and other.toLong()
    is Int -> this.toInt() and other.toInt()
    is Short -> this.toInt() and other.toInt()
    is Byte -> this.toInt() and other.toInt()
    else -> unreachable("Unknown numeric type")
}.cast()

inline infix fun <reified N : Number> N.or(other: N): N = when (this) {
    is Long -> this.toLong() or other.toLong()
    is Int -> this.toInt() or other.toInt()
    is Short -> this.toInt() or other.toInt()
    is Byte -> this.toInt() or other.toInt()
    else -> unreachable("Unknown numeric type")
}.cast()

inline infix fun <reified N : Number> N.xor(other: N): N = when (this) {
    is Long -> this.toLong() xor other.toLong()
    is Int -> this.toInt() xor other.toInt()
    is Short -> this.toInt() xor other.toInt()
    is Byte -> this.toInt() xor other.toInt()
    else -> unreachable("Unknown numeric type")
}.cast()
