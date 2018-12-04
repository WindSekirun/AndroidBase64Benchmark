package com.github.windsekirun.base64benchmark.codec

import com.github.windsekirun.base64benchmark.impl.Base64ByteCodec
import com.github.windsekirun.base64benchmark.impl.Base64Codec
import com.migcomponents.migbase64.Base64

/**
 * Base64Benchmark
 * Class: MiGImpl
 * Created by Pyxis on 2018-12-04.
 *
 *
 * Description:
 */
class MiGImpl : Base64Codec, Base64ByteCodec {
    override fun encode(data: ByteArray): String {
        return Base64.encodeToString(data, false)
    }

    override fun decode(base64: String): ByteArray {
        return Base64.decode(base64)
    }

    override fun encodeBytes(data: ByteArray): ByteArray {
        return Base64.encodeToByte(data, false)
    }

    override fun decodeBytes(base64: ByteArray): ByteArray {
        return Base64.decode(base64)
    }

}
