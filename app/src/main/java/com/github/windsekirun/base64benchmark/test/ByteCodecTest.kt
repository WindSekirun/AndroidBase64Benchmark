package com.github.windsekirun.base64benchmark.test

import com.github.windsekirun.base64benchmark.codec.AndroidImpl
import com.github.windsekirun.base64benchmark.codec.ApacheImpl
import com.github.windsekirun.base64benchmark.codec.Java8Impl
import com.github.windsekirun.base64benchmark.codec.MiGImpl
import com.github.windsekirun.base64benchmark.impl.Base64ByteCodec
import com.github.windsekirun.base64benchmark.model.TestResult
import java.io.File
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

val byteCodecList: List<Base64ByteCodec> = listOf(AndroidImpl(), ApacheImpl(), Java8Impl(), MiGImpl())

fun testBytes(bufferSize: Int): HashMap<String, TestResult> {
    val r = Random(125) //seed is set to make results reproducible
    val buffers = ArrayList<ByteArray>(bufferSize)
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
    val encoded = ArrayList<ByteArray>(buffers.size)
    val start = System.currentTimeMillis()
    for (buf in buffers) encoded.add(codec.encodeBytes(buf))
    val encodeTime = System.currentTimeMillis() - start

    val result = ArrayList<ByteArray>(buffers.size)
    val start2 = System.currentTimeMillis()
    for (ar in encoded) result.add(codec.decodeBytes(ar))
    val decodeTime = System.currentTimeMillis() - start2

    for (i in buffers.indices) {
        if (!Arrays.equals(buffers[i], result[i])) println("Diff at pos = $i")
    }
    return TestResult(encodeTime / 1000.0, decodeTime / 1000.0)
}

