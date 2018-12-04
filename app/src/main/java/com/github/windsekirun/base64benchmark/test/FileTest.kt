package com.github.windsekirun.base64benchmark.test

import com.github.windsekirun.base64benchmark.codec.GuavaImpl
import com.github.windsekirun.base64benchmark.impl.Base64ByteCodec
import com.github.windsekirun.base64benchmark.model.TestResult
import com.github.windsekirun.base64benchmark.model.measureTimeStopWatch
import com.google.common.io.BaseEncoding
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

    // guava methods - Guava doesn't support ByteArray -> ByteArray. so we could implement other way.
    results[GuavaImpl::class.java.simpleName] = testGuavaFile(file)
    return results
}

private fun testGuavaFile(file: File): TestResult {
    val encodeTime = measureTimeStopWatch {
        file.outputStream().use {
            BaseEncoding.base64().encodingStream(it.writer(Charsets.UTF_8))
        }
    }

    return TestResult(encodeTime.toDouble(), 0.0)
}

@Throws(IOException::class)
private fun testByteCodecFile(codec: Base64ByteCodec, buffer: ByteArray): TestResult {
    val encodeTime = measureTimeStopWatch { codec.encodeBytes(buffer) }
    return TestResult(encodeTime.toDouble(), 0.0)
}