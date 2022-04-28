package org.vorpal.research.kthelper

import java.lang.Exception

open class KtException : Exception {
    constructor(message: String) : super(message)
    constructor(message: String, inner: Throwable) : super(message, inner)
}