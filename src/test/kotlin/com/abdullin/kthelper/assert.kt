package com.abdullin.kthelper

import kotlin.test.assertTrue

fun <T> assertEqualsAny(result: T, vararg expectedList: T) {
    var found = false
    for (expected in expectedList) {
        if (result == expected) {
            found = true
            break
        }
    }
    assertTrue(found)
}