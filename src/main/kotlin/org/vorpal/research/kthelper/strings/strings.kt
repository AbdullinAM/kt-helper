@file:Suppress("unused")

package org.vorpal.research.kthelper.strings

import java.util.Locale

fun String.kapitalize() =
    this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

fun String.dekapitalize() =
    this.replaceFirstChar { it.lowercase(Locale.getDefault()) }
