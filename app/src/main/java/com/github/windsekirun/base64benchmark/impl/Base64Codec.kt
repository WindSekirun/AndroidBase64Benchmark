package com.github.windsekirun.base64benchmark.impl

import java.io.IOException

/**
 * Base64Benchmark
 * Class: AndroidImpl
 * Created by Pyxis on 2018-12-04.
 *
 * Description:
 */
interface Base64Codec {
    fun encode(data: ByteArray): String

    @Throws(IOException::class)
    fun decode(base64: String): ByteArray
}