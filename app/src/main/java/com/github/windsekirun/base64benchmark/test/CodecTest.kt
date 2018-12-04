package com.github.windsekirun.base64benchmark.test

import com.github.windsekirun.base64benchmark.Constants.TOTAL_BUFFER_SIZE
import com.github.windsekirun.base64benchmark.codec.*
import com.github.windsekirun.base64benchmark.impl.Base64Codec
import com.github.windsekirun.base64benchmark.model.TestResult
import java.io.IOException
import java.util.*


/**
 * Base64Benchmark
 * Class: CodecTest
 * Created by Pyxis on 2018-12-04.
 *
 * Description:
 */

val codecList: List<Base64Codec> = listOf(AndroidImpl(), ApacheImpl(), Java8Impl(), MiGImpl(), GuavaImpl())

@Throws(IOException::class, InterruptedException::class)
fun testString(bufferSize: Int): Map<String, TestResult> {
    val r = Random(125) //seed is set to make results reproducible
    val buffers = ArrayList<ByteArray>(TOTAL_BUFFER_SIZE / bufferSize)
    for (i in 0 until TOTAL_BUFFER_SIZE / bufferSize) {
        val buf = ByteArray(bufferSize)
        r.nextBytes(buf)
        buffers.add(buf)
    }

    for (codec in codecList) {
        testStringCodec(codec, buffers)
        System.gc()
    }

    val results = HashMap<String, TestResult>(5)
    for (codec in codecList) {
        val name = codec.javaClass.simpleName
        results[name] = testStringCodec(codec, buffers)
        System.gc()
    }

    return results
}

@Throws(IOException::class)
private fun testStringCodec(codec: Base64Codec, buffers: List<ByteArray>): TestResult {
    val encoded = ArrayList<String>(buffers.size)
    val start = System.currentTimeMillis()
    for (buf in buffers) encoded.add(codec.encode(buf))
    val encodeTime = System.currentTimeMillis() - start

    val result = ArrayList<ByteArray>(buffers.size)
    val start2 = System.currentTimeMillis()
    for (s in encoded) result.add(codec.decode(s))
    val decodeTime = System.currentTimeMillis() - start2

    for (i in buffers.indices) {
        if (!Arrays.equals(buffers[i], result[i])) println("Diff at pos = $i")
    }

    return TestResult(encodeTime / 1000.0, decodeTime / 1000.0)
}