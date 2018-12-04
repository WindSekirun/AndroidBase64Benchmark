package com.github.windsekirun.base64benchmark.test

import com.github.windsekirun.base64benchmark.codec.*
import com.github.windsekirun.base64benchmark.impl.Base64Codec
import com.github.windsekirun.base64benchmark.model.TestResult
import com.github.windsekirun.base64benchmark.model.measureTimeStopWatch
import java.io.IOException
import java.util.*


/**
 * Base64Benchmark
 * Class: CodecTest
 * Created by Pyxis on 2018-12-04.
 *
 * Description:
 */

val codecList: List<Base64Codec> =
    listOf(AndroidImpl(), ApacheImpl(), Java8Impl(), MiGImpl(), GuavaImpl())

@Throws(IOException::class, InterruptedException::class)
fun testString(bufferSize: Int): Map<String, TestResult> {
    val r = Random(125)
    val buffers = ArrayList<ByteArray>()
    for (i in 0 until bufferSize) {
        val buf = ByteArray(bufferSize)
        r.nextBytes(buf)
        buffers.add(buf)
    }


    val results = HashMap<String, TestResult>()
    for (codec in codecList) {
        val name = codec.javaClass.simpleName
        results[name] = testStringCodec(codec, buffers)
    }

    return results
}

@Throws(IOException::class)
private fun testStringCodec(codec: Base64Codec, buffers: List<ByteArray>): TestResult {
    val encoded = ArrayList<String>()
    val result = ArrayList<ByteArray>()

    val encodeTime =
        measureTimeStopWatch { for (buf in buffers) encoded.add(codec.encode(buf)) }

    val decodeTime =
        measureTimeStopWatch { for (s in encoded) result.add(codec.decode(s)) }

    return TestResult(encodeTime.toDouble(), decodeTime.toDouble())
}