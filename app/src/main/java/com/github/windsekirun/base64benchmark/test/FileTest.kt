package com.github.windsekirun.base64benchmark.test

import com.github.windsekirun.base64benchmark.impl.Base64ByteCodec
import com.github.windsekirun.base64benchmark.model.TestResult
import java.io.File
import java.io.IOException
import java.util.ArrayList
import kotlin.collections.HashMap
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
    for (codec in byteCodecList) {
        val name = codec.javaClass.simpleName
        val fileBytes = file.readBytes()
        results[name] = testByteCodecFile(codec, fileBytes)
    }

    return results
}

@Throws(IOException::class)
private fun testByteCodecFile(codec: Base64ByteCodec, buffer: ByteArray): TestResult {
    val encoded = ArrayList<ByteArray>()
    val start = System.currentTimeMillis()
    encoded.add(codec.encodeBytes(buffer))
    val encodeTime = System.currentTimeMillis() - start

    return TestResult(encodeTime / 1000.0, 0.0)
}