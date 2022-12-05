package org.vorpal.research.kthelper

import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream

private const val MAX_BYTE_ARRAY_SIZE = 16384

val InputStream.asByteArray: ByteArray get() {
    val buffer = ByteArrayOutputStream()

    var nRead: Int
    val data = ByteArray(MAX_BYTE_ARRAY_SIZE)

    while (this.read(data, 0, data.size).also { nRead = it } != -1) {
        buffer.write(data, 0, nRead)
    }

    return buffer.toByteArray()
}

fun File.write(input: InputStream) = this.writeBytes(input.asByteArray)
