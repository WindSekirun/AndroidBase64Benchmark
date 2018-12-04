package com.github.windsekirun.base64benchmark.codec

import com.github.windsekirun.base64benchmark.impl.Base64ByteCodec
import com.github.windsekirun.base64benchmark.impl.Base64Codec

import java.io.IOException
import java.util.*

/**
 * Base64Benchmark
 * Class: AndroidImpl
 * Created by Pyxis on 2018-12-04.
 *
 * Description:
 */
class Java8Impl : Base64Codec, Base64ByteCodec {
    private val decoder = Base64.getDecoder()
    private val encoder = Base64.getEncoder()

    override fun encode(data: ByteArray): String {
        return encoder.encodeToString(data)
    }

    @Throws(IOException::class)
    override fun decode(base64: String): ByteArray {
        return decoder.decode(base64)
    }

    override fun encodeBytes(data: ByteArray): ByteArray {
        return encoder.encode(data)
    }

    @Throws(IOException::class)
    override fun decodeBytes(base64: ByteArray): ByteArray {
        return decoder.decode(base64)
    }
}