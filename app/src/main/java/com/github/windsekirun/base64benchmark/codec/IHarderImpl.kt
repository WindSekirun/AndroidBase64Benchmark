package com.github.windsekirun.base64benchmark.codec

import com.github.windsekirun.base64benchmark.impl.Base64ByteCodec
import com.github.windsekirun.base64benchmark.impl.Base64Codec
import com.github.windsekirun.base64benchmark.module.IHarderBase64

/**
 * Base64Benchmark
 * Class: IHarderImpl
 * Created by Pyxis on 2018-12-04.
 *
 * Description:
 */
class IHarderImpl : Base64ByteCodec, Base64Codec {
    override fun encodeBytes(data: ByteArray): ByteArray {
        return IHarderBase64.encodeBytesToBytes(data)
    }

    override fun decodeBytes(base64: ByteArray): ByteArray {
        return IHarderBase64.decode(base64)
    }

    override fun encode(data: ByteArray): String {
        return IHarderBase64.encodeBytes(data)
    }

    override fun decode(base64: String): ByteArray {
        return IHarderBase64.decode(base64)
    }

}