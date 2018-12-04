package com.github.windsekirun.base64benchmark.test

import com.github.windsekirun.base64benchmark.impl.Base64ByteCodec
import com.github.windsekirun.base64benchmark.model.TestResult
import com.github.windsekirun.base64benchmark.model.measureTimeStopWatch
import java.io.File
import java.io.IOException
import kotlin.collections.set

/**
 * Base64Benchmark
 * Class: FileTest
 * Created by Pyxis on 2018-12-04.
 *
 * Description:
 */

fun testFile(file: File): HashMap<String, TestResult> {
    val results = HashMap<String, TestResult>()
    val fileBytes = file.readBytes()

    for (codec in byteCodecList) {
        val name = codec.javaClass.simpleName
        results[name] = testByteCodecFile(codec, fileBytes)
    }

    // Guava doesn't support ByteArray -> ByteArray. so we ignore them.
    return results
}


@Throws(IOException::class)
private fun testByteCodecFile(codec: Base64ByteCodec, buffer: ByteArray): TestResult {
    val encodeTime = measureTimeStopWatch { codec.encodeBytes(buffer) }
    return TestResult(encodeTime.toDouble(), 0.0)
}