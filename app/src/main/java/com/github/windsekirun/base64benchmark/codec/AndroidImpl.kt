package com.github.windsekirun.base64benchmark.codec

import android.util.Base64
import com.github.windsekirun.base64benchmark.impl.Base64ByteCodec
import com.github.windsekirun.base64benchmark.impl.Base64Codec

/**
 * Base64Benchmark
 * Class: AndroidImpl
 * Created by Pyxis on 2018-12-04.
 *
 * Description:
 */
class AndroidImpl : Base64Codec, Base64ByteCodec {
    private val flag = Base64.DEFAULT

    override fun decodeBytes(base64: ByteArray): ByteArray {
        return Base64.decode(base64, flag)
    }

    override fun encodeBytes(data: ByteArray): ByteArray {
        return Base64.encode(data, flag)
    }

    override fun encode(data: ByteArray): String {
        return Base64.encodeToString(data, flag)
    }

    override fun decode(base64: String): ByteArray {
        return Base64.decode(base64, flag)
    }

}