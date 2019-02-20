package io.spring.demo.scriptengine

import java.io.File
import java.io.FileInputStream
import java.nio.file.Files
import java.util.jar.JarInputStream
import kotlin.script.experimental.jvm.util.scriptCompilationClasspathFromContextOrStlib

val springBootFatJar: File? by lazy {
    Thread.currentThread().contextClassLoader.getResource("BOOT-INF/classes")?.path?.substringBefore("!/")?.substringAfter("file:").toFile()
}

val springBootClassPath: List<File> by lazy {
    if (springBootFatJar == null) {
        scriptCompilationClasspathFromContextOrStlib("kotlin-script-util.jar", wholeClasspath = true)
    } else {
        val unpackDir = Files.createTempDirectory("boot-").toFile().apply {
            Runtime.getRuntime().addShutdownHook(Thread {
                deleteRecursively()
            })
        }
        ArrayList<File>().apply {
            JarInputStream(FileInputStream(springBootFatJar)).use { jarInputStream ->
                add(File("$unpackDir/BOOT-INF/classes"))
                do {
                    val entry = jarInputStream.nextJarEntry
                    if (entry != null) {
                        try {
                            if (!entry.isDirectory) {
                                val file = File("$unpackDir/${entry.name}")
                                if (entry.name.startsWith("BOOT-INF/lib")) {
                                    add(file)
                                }
                                Files.createDirectories(file.parentFile.toPath())
                                file.outputStream().use { outputStream ->
                                    jarInputStream.copyTo(outputStream)
                                    outputStream.flush()
                                }
                            }
                        } finally {
                            jarInputStream.closeEntry()
                        }
                    }
                } while (entry != null)
            }
        }
    }
}

private fun String?.toFile(): File? {
    return if (this == null) null else File(this)
}
