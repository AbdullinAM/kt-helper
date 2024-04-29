package org.vorpal.research.kthelper

import org.vorpal.research.kthelper.assert.unreachable
import java.nio.file.Path
import java.nio.file.Paths


fun getJDKPath(): Path {
    return Paths.get(System.getProperty("java.home")).parent.toAbsolutePath()
}

fun getJavaPath(): Path = Paths.get(System.getProperty("java.home"), "bin", "java").toAbsolutePath()

fun getJavacPath(): Path = Paths.get(System.getProperty("java.home"), "bin", "javac").toAbsolutePath()

fun getJvmVersion(): Int {
    val versionStr = System.getProperty("java.version")
    return """(1.)?(\d+)""".toRegex().find(versionStr)?.let {
        it.groupValues[2].toInt()
    } ?: unreachable("Could not detect JVM version: \"$versionStr\"")
}
