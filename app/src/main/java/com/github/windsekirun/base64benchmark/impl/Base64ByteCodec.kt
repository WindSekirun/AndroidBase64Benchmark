package com.github.windsekirun.base64benchmark.impl

import java.io.IOException

/**
 * Base64Benchmark
 * Class: AndroidImpl
 * Created by Pyxis on 2018-12-04.
 *
 * Description:
 */
interface Base64ByteCodec {
    fun encodeBytes(data: ByteArray): ByteArray

    @Throws(IOException::class)
    fun decodeBytes(base64: ByteArray): ByteArray
}
