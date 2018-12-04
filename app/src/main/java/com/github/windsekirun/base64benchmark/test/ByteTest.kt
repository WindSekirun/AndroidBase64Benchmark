package com.github.windsekirun.base64benchmark.test

import com.github.windsekirun.base64benchmark.Constants.byteCodecList
import com.github.windsekirun.base64benchmark.impl.Base64ByteCodec
import com.github.windsekirun.base64benchmark.model.TestResult
import com.github.windsekirun.base64benchmark.model.measureTimeStopWatch
import java.io.IOException
import java.util.*
import kotlin.collections.HashMap


/**
 * Base64Benchmark
 * Class: TestBytes
 * Created by Pyxis on 2018-12-04.
 *
 * Description:
 */


fun testBytes(bufferSize: Int): HashMap<String, TestResult> {
    val r = Random(125)
    val buffers = ArrayList<ByteArray>()
    for (i in 0 until bufferSize) {
        val buf = ByteArray(bufferSize)
        r.nextBytes(buf)
        buffers.add(buf)
    }

    val results = HashMap<String, TestResult>()
    for (codec in byteCodecList) {
        val name = codec.javaClass.simpleName
        results[name] = testByteCodec(codec, buffers)
    }

    return results
}

@Throws(IOException::class)
private fun testByteCodec(codec: Base64ByteCodec, buffers: List<ByteArray>): TestResult {
    val encoded = ArrayList<ByteArray>()
    val result = ArrayList<ByteArray>()

    val encodeTime =
        measureTimeStopWatch { for (buf in buffers) encoded.add(codec.encodeBytes(buf)) }

    val decodeTime =
        measureTimeStopWatch { for (ar in encoded) result.add(codec.decodeBytes(ar)) }

    return TestResult(encodeTime.toDouble(), decodeTime.toDouble())
}
