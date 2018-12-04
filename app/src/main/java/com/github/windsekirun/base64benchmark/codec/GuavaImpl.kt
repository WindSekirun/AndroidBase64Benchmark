package com.github.windsekirun.base64benchmark.codec

import com.github.windsekirun.base64benchmark.impl.Base64Codec
import com.google.common.io.BaseEncoding

/**
 * Base64Benchmark
 * Class: GuavaImpl
 * Created by Pyxis on 2018-12-04.
 *
 * Description:
 */

class GuavaImpl : Base64Codec {
    override fun encode(data: ByteArray): String {
        return BaseEncoding.base64().encode(data)
    }

    override fun decode(base64: String): ByteArray {
        return BaseEncoding.base64().decode(base64)
    }
}