@file:Suppress("unused")

package org.vorpal.research.kthelper

import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream
import java.nio.file.FileVisitResult
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes

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

fun Path.resolve(vararg names: String): Path {
    var current = this
    for (name in names) {
        current = current.resolve(name)
    }
    return current
}

fun deleteOnExit(file: File) = deleteOnExit(file.toPath())
fun deleteOnExit(directoryToBeDeleted: Path) = Runtime.getRuntime().addShutdownHook(Thread {
    tryOrNull {
        Files.walkFileTree(directoryToBeDeleted, object : SimpleFileVisitor<Path>() {
            override fun visitFile(
                file: Path,
                @SuppressWarnings("unused") attrs: BasicFileAttributes
            ): FileVisitResult {
                file.toFile().deleteOnExit()
                return FileVisitResult.CONTINUE
            }

            override fun preVisitDirectory(
                dir: Path,
                @SuppressWarnings("unused") attrs: BasicFileAttributes
            ): FileVisitResult {
                dir.toFile().deleteOnExit()
                return FileVisitResult.CONTINUE
            }
        })
    }
})
