package com.github.windsekirun.base64benchmark.codec

import com.github.windsekirun.base64benchmark.impl.Base64ByteCodec
import com.github.windsekirun.base64benchmark.impl.Base64Codec
import org.apache.commons.codec.binary.Base64

/**
 * Base64Benchmark
 * Class: ApacheImpl
 * Created by Pyxis on 2018-12-04.
 *
 * Description:
 */
class ApacheImpl : Base64ByteCodec, Base64Codec {
    override fun encodeBytes(data: ByteArray): ByteArray {
        return Base64.encodeBase64(data)
    }

    override fun decodeBytes(base64: ByteArray): ByteArray {
        return Base64.decodeBase64(base64)
    }

    override fun encode(data: ByteArray): String {
        return Base64.encodeBase64String(data)
    }

    override fun decode(base64: String): ByteArray {
        return Base64.decodeBase64(base64)
    }

}