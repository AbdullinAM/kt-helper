package com.abdullin.kthelper.collection

import org.junit.Test

import org.junit.Assert.*
import java.util.*
import kotlin.random.Random
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

class StackTest {
    lateinit var stack: Stack<Int>

    @BeforeTest
    fun init() {
        stack = stackOf()
    }

    @AfterTest
    fun cleanup() {
        stack.clear()
    }

    private fun fillStack() {
        val targetSize = Random.nextInt(100)
        for (index in 1..targetSize) {
            stack.push(Random.nextInt())
        }
    }

    @Test
    fun stackTest() {
        assertTrue(stack.size == 0)

        val oracleImpl = ArrayDeque<Int>()
        val numberOfOperations = Random.nextInt(1000)
        repeat(numberOfOperations) {
            val isPush = Random.nextBoolean()
            if (isPush) {
                val value = Random.nextInt()
                val oldSize = stack.size
                stack.push(value)
                oracleImpl.push(value)
                assertEquals(oldSize + 1, stack.size)
                assertEquals(oracleImpl.size, stack.size)
                assertEquals(oracleImpl.peek(), stack.peek())
            } else {
                assertEquals(oracleImpl.size, stack.size)
                if (stack.isNotEmpty()) {
                    assertEquals(oracleImpl.pop(), stack.pop())
                    assertEquals(oracleImpl.size, stack.size)
                }
            }
        }
    }

    @Test
    fun popOrNull() {
        assertTrue(stack.isEmpty())
        assertNull(stack.popOrNull())

        fillStack()
        while (stack.isNotEmpty()) {
            assertNotNull(stack.popOrNull())
        }

        assertNull(stack.popOrNull())
    }

    @Test
    operator fun iterator() {
        val oracleImpl = ArrayDeque<Int>()
        val targetSize = Random.nextInt(100)
        for (index in 1..targetSize) {
            val next = Random.nextInt()
            stack.push(next)
            oracleImpl.push(next)
        }

        val stackIterator = stack.iterator()
        val oracleIterator = oracleImpl.iterator()
        while (stackIterator.hasNext()) {
            assertTrue(oracleIterator.hasNext())
            assertEquals(oracleIterator.next(), stackIterator.next())
        }
        assertEquals(oracleIterator.hasNext(), stackIterator.hasNext())
    }
}