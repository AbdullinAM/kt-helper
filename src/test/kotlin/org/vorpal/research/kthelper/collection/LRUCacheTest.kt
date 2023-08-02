package org.vorpal.research.kthelper.collection

import org.junit.Assert.*
import org.junit.Test
import org.vorpal.research.kthelper.KtException
import kotlin.random.Random

class LRUCacheTest {
    @Test
    fun testCache() {
        assertThrows(KtException::class.java) { LRUCache<Int, Int>(0U) }

        val sizes = listOf(1U, 100U, 10_000U, 100_000U)
        for (size in sizes) {
            val cache = LRUCache<Int, Int>(size)
            val currentValues = HashMap<Int, Int>()
            val random = Random(size.toInt())

            repeat(1_000_000) {
                val next = random.nextInt()
                val value = random.nextInt()
                when (next) {
                    in currentValues -> {
                        if (next in cache) {
                            assertEquals(currentValues[next], cache[next])
                        }

                        val oldSize = cache.size
                        cache[next] = value
                        currentValues[next] = value
                        assertTrue(oldSize <= size)
                        assertEquals(currentValues[next], cache[next])
                        assertEquals(oldSize, cache.size)
                    }
                    else -> {
                        assertFalse(next in cache)
                        val oldSize = cache.size

                        cache[next] = value
                        currentValues[next] = value
                        if (oldSize < size) {
                            assertEquals(oldSize + 1U, cache.size)
                        } else {
                            assertEquals(oldSize, cache.size)
                        }
                        assertEquals(currentValues[next], cache[next])
                    }
                }
            }
        }
    }
}
